package com.project.dvc_barber_service.controller.v1.barber.service.models;

import com.project.dvc_barber_service.util.request.MediaRequest;

import java.util.List;

public record BarberServiceUpdateRequest(
        Long barberCategoryId,
        String serviceName,
        String description,
        Long price,
        Integer estimateDuration,
        String durationTypeCode,
        String thumbnail,
        List<MediaRequest> serviceImagesMedia
) {
}
