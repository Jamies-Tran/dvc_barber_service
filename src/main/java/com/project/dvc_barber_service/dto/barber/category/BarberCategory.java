package com.project.dvc_barber_service.dto.barber.category;

import lombok.Builder;

@Builder
public record BarberCategory(
        Long barberCategoryId,
        String categoryCode,
        String categoryName,
        String statusCode,
        String statusName
) {
}
