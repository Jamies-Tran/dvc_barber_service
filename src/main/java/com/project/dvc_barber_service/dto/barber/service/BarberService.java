package com.project.dvc_barber_service.dto.barber.service;

import com.project.dvc_barber_service.dto.media.Media;
import com.project.dvc_barber_service.enums.duration.EDurationType;
import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Builder
public record BarberService(
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
        List<Media> serviceImagesMedia,
        String statusCode,
        String statusName
) {
    public BarberService {
        if(!StringUtils.hasText(durationTypeName)) {
            Optional<EDurationType> durationType = EDurationType.getByCode(durationTypeCode);
            if(durationType.isPresent()) {
                durationTypeName = durationType.get().getName();
            }
        }
    }
}
