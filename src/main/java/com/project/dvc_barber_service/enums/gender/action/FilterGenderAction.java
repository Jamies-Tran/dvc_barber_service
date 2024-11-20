package com.project.dvc_barber_service.enums.gender.action;

import lombok.Builder;

@Builder
public record FilterGenderAction(String name) {
    public static FilterGenderAction buildFrom(String name) {
        return FilterGenderAction.builder().name(name).build();
    }
}
