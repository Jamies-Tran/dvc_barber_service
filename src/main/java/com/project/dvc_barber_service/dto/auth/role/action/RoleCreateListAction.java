package com.project.dvc_barber_service.dto.auth.role.action;

import com.project.dvc_barber_service.dto.auth.role.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record RoleCreateListAction(List<Role> roles) {
    public static RoleCreateListAction buildFrom(List<Role> roles) {
        return RoleCreateListAction.builder()
                .roles(roles)
                .build();
    }
}
