package com.project.dvc_barber_service.dto.barber.category;

import com.project.dvc_barber_service.dto.barber.service.BarberService;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record BarberCategory(
        Long barberCategoryId,
        String categoryCode,
        String categoryName,
        @With List<BarberService> services,
        String statusCode,
        String statusName
) {
}
