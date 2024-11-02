package com.project.dvc_barber_service.controller.auth.authorization.models;

import lombok.Builder;

@Builder
public record PermissionRequest(
        String permissionKey,
        String permissionCode,
        String permissionName
) {
}
