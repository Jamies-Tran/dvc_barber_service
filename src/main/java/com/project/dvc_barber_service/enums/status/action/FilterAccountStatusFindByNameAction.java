package com.project.dvc_barber_service.enums.status.action;

import lombok.Builder;

@Builder
public record FilterAccountStatusFindByNameAction(
        String name
) {
    public static FilterAccountStatusFindByNameAction buildFrom(String name) {
        return FilterAccountStatusFindByNameAction.builder().name(name).build();
    }
}
