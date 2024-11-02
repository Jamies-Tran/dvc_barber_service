package com.project.dvc_barber_service.dto.auth.role.action;

import lombok.Builder;

@Builder
public record RoleFindByCodeAction(String roleCode) {
    public static RoleFindByCodeAction buildFrom(String roleCode) {
        return RoleFindByCodeAction.builder().roleCode(roleCode).build();
    }
}
