package com.project.dvc_barber_service.controller.v1.account.models.role;

import lombok.Builder;

import java.util.List;

@Builder
public record AccountRoleResponse(
        String roleCode,
        String roleName,
        List<String> authorities
) {
}
