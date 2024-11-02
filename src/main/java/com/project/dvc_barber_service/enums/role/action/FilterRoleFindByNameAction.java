package com.project.dvc_barber_service.enums.role.action;

import lombok.Builder;

@Builder
public record FilterRoleFindByNameAction(String name) {
    public static FilterRoleFindByNameAction buildFrom(String name) {
        return FilterRoleFindByNameAction.builder().name(name).build();
    }
}
