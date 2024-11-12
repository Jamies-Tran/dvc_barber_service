package com.project.dvc_barber_service.controller.v1.account.models;

import lombok.Builder;

@Builder
public record UpdatePasswordRequest(
        String oldPassword,
        String newPassword
) {
}
