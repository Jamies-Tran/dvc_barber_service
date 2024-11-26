package com.project.dvc_barber_service.controller.v1.barber.service.models;

import lombok.Builder;

@Builder
public record BarberServiceRequest(
        Long barberCategoryId,
        String serviceName,
        String description,
        Long price,
        Integer estimateDuration,
        String durationTypeCode,
        String thumbnail
) {
}
