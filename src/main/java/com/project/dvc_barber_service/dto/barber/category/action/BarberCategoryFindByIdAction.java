package com.project.dvc_barber_service.dto.barber.category.action;

import lombok.Builder;

@Builder
public record BarberCategoryFindByIdAction(
        Long barberCategoryId
) {
    public static BarberCategoryFindByIdAction buildFrom(Long barberCategoryId) {
        return BarberCategoryFindByIdAction.builder()
                .barberCategoryId(barberCategoryId)
                .build();
    }
}
