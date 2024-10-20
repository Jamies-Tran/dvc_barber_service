package com.project.dvc_barber_service.dto.auth;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RefreshToken(
        String refreshToken,
        String phone,
        LocalDateTime expiresAt
) {
    public static RefreshToken buildFrom(String refreshToken, String phone) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .phone(phone)
                .build();
    }
}
