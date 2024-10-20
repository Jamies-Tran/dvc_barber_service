package com.project.dvc_barber_service.dto.auth.permission.action;

import lombok.Builder;

@Builder
public record PermissionFindByRoleIdAction(
        Long roleId
) {
    public static PermissionFindByRoleIdAction buildFrom(Long roleId) {
        return PermissionFindByRoleIdAction.builder()
                .roleId(roleId)
                .build();
    }
}
