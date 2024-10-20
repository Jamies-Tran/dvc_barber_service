package com.project.dvc_barber_service.dto.auth.role.permission.action;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import lombok.Builder;

import java.util.List;

@Builder
public record RolePermissionCreateListAction(
        Long roleId,
        List<Permission> permissions
) {
    public static RolePermissionCreateListAction buildFrom(Long roleId, List<Permission> permissions) {
        return RolePermissionCreateListAction.builder()
                .roleId(roleId)
                .permissions(permissions)
                .build();
    }
}
