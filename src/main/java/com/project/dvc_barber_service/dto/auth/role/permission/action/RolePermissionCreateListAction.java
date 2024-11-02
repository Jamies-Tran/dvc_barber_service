package com.project.dvc_barber_service.dto.auth.role.permission.action;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record RolePermissionCreateListAction(
        Role role,
        List<Permission> permissions
) {
    public static RolePermissionCreateListAction buildFrom(Role role, List<Permission> permissions) {
        return RolePermissionCreateListAction.builder()
                .role(role)
                .permissions(permissions)
                .build();
    }
}
