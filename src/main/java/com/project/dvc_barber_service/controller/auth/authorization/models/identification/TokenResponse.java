package com.project.dvc_barber_service.controller.auth.authorization.models.identification;

import lombok.Builder;

@Builder
public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
