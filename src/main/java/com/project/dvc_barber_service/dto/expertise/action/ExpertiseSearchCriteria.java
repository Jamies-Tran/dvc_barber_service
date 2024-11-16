package com.project.dvc_barber_service.dto.expertise.action;

import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Builder
public record ExpertiseSearchCriteria(
        String search,
        List<String> expertiseCodes
) {
    public static ExpertiseSearchCriteria buildFrom(String search, List<String> expertiseCodes) {
        return ExpertiseSearchCriteria.builder()
                .search(search)
                .expertiseCodes(expertiseCodes)
                .build();
    }

    public Boolean hasSearchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean hasExpertiseCodesEmpty() {
        return Objects.isNull(expertiseCodes) || expertiseCodes.isEmpty();
    }
}
