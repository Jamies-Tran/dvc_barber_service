package com.project.dvc_barber_service.dto.auth.permission.action;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import lombok.Builder;

import java.util.List;

@Builder
public record PermissionCreateListAction(
        List<Permission> permissions
) {
    public static PermissionCreateListAction buildFrom(List<Permission> permissions) {
        return PermissionCreateListAction.builder()
                .permissions(permissions)
                .build();
    }
}
