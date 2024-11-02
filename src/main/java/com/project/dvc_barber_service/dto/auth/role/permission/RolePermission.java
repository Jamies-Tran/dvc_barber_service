package com.project.dvc_barber_service.dto.auth.role.permission;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.Role;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record RolePermission(
        Long rolePermissionId,
        Long roleId,
        Long permissionId,
        String roleCode,
        String permissionCode,
        Role role,
        Permission permission
) {
    public static RolePermission buildFrom(Long roleId, Long permissionId, String roleCode, String permissionCode) {
        return RolePermission.builder()
                .roleId(roleId)
                .permissionId(permissionId)
                .roleCode(roleCode)
                .permissionCode(permissionCode)
                .build();
    }
}
