package com.project.dvc_barber_service.dto.auth.identification;

import lombok.Builder;

@Builder
public record Token(
        String accessToken,
        String refreshToken
) {
    public static Token buildFrom(String accessToken, String refreshToken) {
        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
