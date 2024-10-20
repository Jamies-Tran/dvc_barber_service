package com.project.dvc_barber_service.controller.authorization.models.identification;

import lombok.Builder;

@Builder
public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
