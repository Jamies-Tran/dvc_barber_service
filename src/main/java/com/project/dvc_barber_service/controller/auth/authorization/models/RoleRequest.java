package com.project.dvc_barber_service.controller.auth.authorization.models;

import lombok.Builder;

import java.util.List;

@Builder
public record RoleRequest(
        String roleCode,
        String roleName,
        List<PermissionRequest> permissions
) {
}
