package com.project.dvc_barber_service.dto.barber.service.action;

import com.project.dvc_barber_service.dto.barber.service.BarberService;
import lombok.Builder;

@Builder
public record BarberServiceCreateAction(BarberService barberService) {
    public static BarberServiceCreateAction buildFrom(BarberService barberService) {
        return BarberServiceCreateAction.builder()
                .barberService(barberService)
                .build();
    }
}
