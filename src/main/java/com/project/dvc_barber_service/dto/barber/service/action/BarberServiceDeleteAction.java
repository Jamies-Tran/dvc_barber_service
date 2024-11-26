package com.project.dvc_barber_service.dto.barber.service.action;

import lombok.Builder;

@Builder
public record BarberServiceDeleteAction(Long barberServiceId) {
    public static BarberServiceDeleteAction buildFrom(Long barberServiceId) {
        return BarberServiceDeleteAction.builder()
                .barberServiceId(barberServiceId)
                .build();
    }
}
