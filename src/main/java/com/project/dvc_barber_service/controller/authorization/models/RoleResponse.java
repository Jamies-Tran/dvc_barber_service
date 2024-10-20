package com.project.dvc_barber_service.controller.authorization.models;

import lombok.Builder;

import java.util.List;

@Builder
public record RoleResponse(
        String roleCode,
        String roleName,
        List<String> authorities
) {
}
