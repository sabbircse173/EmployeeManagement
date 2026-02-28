package com.ideascale.data;

public record TokenRefreshResponse(
        String accessToken,
        String tokenType
) {
    public TokenRefreshResponse(String accessToken) {
        this(accessToken, "Bearer");
    }
}
