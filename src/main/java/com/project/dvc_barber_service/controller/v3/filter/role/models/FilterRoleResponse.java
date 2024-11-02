package com.project.dvc_barber_service.controller.v3.filter.role.models;

import com.project.dvc_barber_service.enums.role.ERole;
import lombok.Builder;

@Builder
public record FilterRoleResponse(
        String code,
        String name
) {
    public static FilterRoleResponse buildFrom(ERole role) {
        return FilterRoleResponse.builder()
                .code(role.getCode())
                .name(role.getName())
                .build();
    }
}
