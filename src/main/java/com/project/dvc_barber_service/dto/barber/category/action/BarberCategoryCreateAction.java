package com.project.dvc_barber_service.dto.barber.category.action;

import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import lombok.Builder;

@Builder
public record BarberCategoryCreateAction(BarberCategory barberCategory) {
    public static BarberCategoryCreateAction buildFrom(BarberCategory barberCategory) {
        return BarberCategoryCreateAction.builder()
                .barberCategory(barberCategory)
                .build();
    }
}
