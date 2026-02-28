package com.ideascale.data;

public record JwtResponse(
        String accessToken,
        String tokenType,
        String username,
        String role
) {
    public JwtResponse(String accessToken, String username, String role) {
        this(accessToken, "Bearer", username, role);
    }
}
