package com.project.dvc_barber_service.dto.barber.category.action;

import lombok.Builder;

@Builder
public record BarberCategoryDeleteAction(
        Long barberCategoryId
) {
    public static BarberCategoryDeleteAction buildFrom(Long barberCategoryId) {
        return BarberCategoryDeleteAction.builder()
                .barberCategoryId(barberCategoryId)
                .build();
    }
}
