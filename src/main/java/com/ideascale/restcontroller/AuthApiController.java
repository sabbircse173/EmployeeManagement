package com.ideascale.restcontroller;

import com.ideascale.constants.Path;
import com.ideascale.data.JwtResponse;
import com.ideascale.data.LoginRequest;
import com.ideascale.data.TokenRefreshResponse;
import com.ideascale.entity.RefreshToken;
import com.ideascale.exception.TokenRefreshException;
import com.ideascale.security.JwtTokenProvider;
import com.ideascale.security.CustomUserDetailsService;
import com.ideascale.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Path.AUTH_API_PATH)
public class AuthApiController {

    private static final String REFRESH_COOKIE_NAME = "refreshToken";
    private static final int REFRESH_COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // 7 days in seconds

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService userDetailsService;

    public AuthApiController(AuthenticationManager authenticationManager,
                             JwtTokenProvider jwtTokenProvider,
                             RefreshTokenService refreshTokenService,
                             CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(Path.LOGIN)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName());

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        ResponseCookie cookie = buildRefreshCookie(refreshToken.getToken(), REFRESH_COOKIE_MAX_AGE);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new JwtResponse(accessToken, authentication.getName(), role));
    }

    @PostMapping(Path.REFRESH)
    public ResponseEntity<TokenRefreshResponse> refresh(
            @CookieValue(name = REFRESH_COOKIE_NAME, required = false) String rawToken) {

        if (rawToken == null || rawToken.isBlank()) {
            throw new TokenRefreshException("Refresh token cookie missing. Please log in again.");
        }

        RefreshToken refreshToken = refreshTokenService.findByToken(rawToken)
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new TokenRefreshException("Refresh token not found. Please log in again."));

        // Token rotation: delete old token, issue new one
        refreshTokenService.deleteByToken(refreshToken.getToken());
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(refreshToken.getUsername());

        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshToken.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        String newAccessToken = jwtTokenProvider.generateToken(authentication);

        ResponseCookie cookie = buildRefreshCookie(newRefreshToken.getToken(), REFRESH_COOKIE_MAX_AGE);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new TokenRefreshResponse(newAccessToken));
    }

    @PostMapping(Path.LOGOUT)
    public ResponseEntity<Void> logout(
            @CookieValue(name = REFRESH_COOKIE_NAME, required = false) String rawToken) {

        if (rawToken != null && !rawToken.isBlank()) {
            refreshTokenService.deleteByToken(rawToken);
        }

        ResponseCookie clearCookie = buildRefreshCookie("", 0);

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, clearCookie.toString())
                .build();
    }

    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<String> handleTokenRefreshException(TokenRefreshException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    private ResponseCookie buildRefreshCookie(String value, int maxAge) {
        return ResponseCookie.from(REFRESH_COOKIE_NAME, value)
                .httpOnly(true)
                .secure(false)          // set to true in production (HTTPS)
                .sameSite("Strict")
                .path("/api/auth")      // scoped: only sent to /api/auth/** endpoints
                .maxAge(maxAge)
                .build();
    }
}
