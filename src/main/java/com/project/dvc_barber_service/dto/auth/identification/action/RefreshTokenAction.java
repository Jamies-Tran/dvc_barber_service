package com.project.dvc_barber_service.dto.auth.identification.action;

import lombok.Builder;

@Builder
public record RefreshTokenAction(
        String refreshToken
) {
    public static RefreshTokenAction buildFrom(String refreshToken) {
        return RefreshTokenAction.builder()
                .refreshToken(refreshToken)
                .build();
    }
}
