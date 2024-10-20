package com.project.dvc_barber_service.controller.authorization.models;

import lombok.Builder;

@Builder
public record PermissionRequest(
        String permissionFor,
        String permissionCode,
        String permissionName
) {
}
