package com.project.dvc_barber_service.dto.barber.service.action;

import lombok.Builder;

@Builder
public record BarberServiceFindByIdAction(
        Long barberServiceId
) {
    public static BarberServiceFindByIdAction buildFrom(Long barberServiceId) {
        return BarberServiceFindByIdAction.builder()
                .barberServiceId(barberServiceId)
                .build();
    }
}
