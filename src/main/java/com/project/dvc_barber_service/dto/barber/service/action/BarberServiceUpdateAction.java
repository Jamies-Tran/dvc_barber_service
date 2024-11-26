package com.project.dvc_barber_service.dto.barber.service.action;

import com.project.dvc_barber_service.dto.barber.service.BarberService;
import lombok.Builder;

@Builder
public record BarberServiceUpdateAction(
        Long barberServiceId,
        BarberService barberService
) {
    public static BarberServiceUpdateAction buildFrom(Long barberServiceId, BarberService barberService) {
        return BarberServiceUpdateAction.builder()
                .barberServiceId(barberServiceId)
                .barberService(barberService)
                .build();
    }
}
