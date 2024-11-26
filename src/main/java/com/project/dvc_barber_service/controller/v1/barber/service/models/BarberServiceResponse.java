package com.project.dvc_barber_service.controller.v1.barber.service.models;

import com.project.dvc_barber_service.dto.media.Media;
import lombok.Builder;

import java.util.List;

@Builder
public record BarberServiceResponse(
        Long barberServiceId,
        Long barberCategoryId,
        String serviceCode,
        String serviceName,
        String description,
        Long price,
        Integer estimateDuration,
        String durationTypeCode,
        String durationTypeName,
        String thumbnail,
        List<Media> serviceImagesMedia
) {
}
