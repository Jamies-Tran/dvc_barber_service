package com.project.dvc_barber_service.dto.auth.role.action;

import lombok.Builder;

@Builder
public record RoleFindByIdAction(Long roleId) {
    public static RoleFindByIdAction buildFrom(Long roleId) {
        return RoleFindByIdAction.builder()
                .roleId(roleId)
                .build();
    }
}
