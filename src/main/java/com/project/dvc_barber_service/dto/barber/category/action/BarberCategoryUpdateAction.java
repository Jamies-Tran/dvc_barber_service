package com.project.dvc_barber_service.dto.barber.category.action;

import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import lombok.Builder;

@Builder
public record BarberCategoryUpdateAction(Long barberCategoryId, BarberCategory barberCategory) {
    public static BarberCategoryUpdateAction buildFrom(Long barberCategoryId, BarberCategory barberCategory) {
        return BarberCategoryUpdateAction.builder()
                .barberCategoryId(barberCategoryId)
                .barberCategory(barberCategory)
                .build();
    }
}
