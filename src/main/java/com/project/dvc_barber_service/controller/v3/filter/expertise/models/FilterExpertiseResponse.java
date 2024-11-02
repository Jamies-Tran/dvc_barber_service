package com.project.dvc_barber_service.controller.v3.filter.expertise.models;

import com.project.dvc_barber_service.enums.expertise.EExpertise;
import lombok.Builder;

@Builder
public record FilterExpertiseResponse(
        String code,
        String name
) {
    public static FilterExpertiseResponse buildFrom(EExpertise expertise) {
        return FilterExpertiseResponse.builder()
                .code(expertise.getCode())
                .name(expertise.getName())
                .build();
    }
}
