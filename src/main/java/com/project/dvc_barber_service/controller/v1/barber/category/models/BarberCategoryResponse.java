package com.project.dvc_barber_service.controller.v1.barber.category.models;

import com.project.dvc_barber_service.controller.v1.barber.service.models.BarberServiceResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record BarberCategoryResponse(
        Long barberCategoryId,
        String categoryCode,
        String categoryName,
        List<BarberServiceResponse> services,
        String statusCode,
        String statusName
) {
}
