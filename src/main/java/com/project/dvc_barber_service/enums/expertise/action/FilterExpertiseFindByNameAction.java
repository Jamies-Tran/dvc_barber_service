package com.project.dvc_barber_service.enums.expertise.action;

import lombok.Builder;

@Builder
public record FilterExpertiseFindByNameAction(String name) {
    public static FilterExpertiseFindByNameAction buildFrom(String name) {
        return FilterExpertiseFindByNameAction.builder().name(name).build();
    }
}
