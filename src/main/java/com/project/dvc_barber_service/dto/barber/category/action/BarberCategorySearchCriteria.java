package com.project.dvc_barber_service.dto.barber.category.action;

import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Builder
public record BarberCategorySearchCriteria(
        String search,
        List<String> categoryCodes
) {
    public static BarberCategorySearchCriteria buildFrom(String search, List<String> categoryCodes) {
        return BarberCategorySearchCriteria.builder()
                .search(search)
                .categoryCodes(categoryCodes)
                .build();
    }

    public Boolean hasSearchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean hasCategoryCodesEmpty() {
        return Objects.isNull(categoryCodes) || categoryCodes.isEmpty();
    }
}
