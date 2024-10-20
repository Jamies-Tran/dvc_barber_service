package com.project.dvc_barber_service.dto.auth.role.action;

import com.project.dvc_barber_service.dto.auth.role.Role;
import lombok.Builder;

@Builder
public record RoleCreateAction(
        Role role
) {
    public static RoleCreateAction buildFrom(Role role) {
        return RoleCreateAction.builder()
                .role(role)
                .build();
    }
}
