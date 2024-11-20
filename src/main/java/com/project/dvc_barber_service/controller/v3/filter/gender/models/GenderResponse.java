package com.project.dvc_barber_service.controller.v3.filter.gender.models;

import com.project.dvc_barber_service.enums.gender.EGender;
import lombok.Builder;

@Builder
public record GenderResponse(
        String code,
        String name
) {
    public static GenderResponse buildFrom(EGender gender) {
        return GenderResponse.builder()
                .code(gender.getCode())
                .name(gender.getName())
                .build();
    }
}
