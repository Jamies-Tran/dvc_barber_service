package com.project.dvc_barber_service.controller.v1.barber.category.models;

import lombok.Builder;

@Builder
public record BarberCategoryResponse(
        Long barberCategoryId,
        String categoryCode,
        String categoryName,
        String statusCode,
        String statusName
) {
}
