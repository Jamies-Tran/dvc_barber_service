package com.project.dvc_barber_service.controller.v1.barber.category.models;

import lombok.Builder;

@Builder
public record BarberCategoryRequest(
        String categoryName
) {
}
