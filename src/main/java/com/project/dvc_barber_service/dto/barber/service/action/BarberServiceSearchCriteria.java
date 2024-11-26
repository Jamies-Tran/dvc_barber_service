package com.project.dvc_barber_service.dto.barber.service.action;

import com.project.dvc_barber_service.util.validate.ValidateRange;
import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Builder
public record BarberServiceSearchCriteria(
        String search,
        String durationTypeCode,
        Long branchId,
        Long categoryId,
        List<Long> priceRange,
        List<Integer> estimateDurationRange
) {
    public static BarberServiceSearchCriteria buildFrom(String search,
                                                        String durationTypeCode,
                                                        Long branchId,
                                                        Long categoryId,
                                                        List<Long> priceRange,
                                                        List<Integer> estimateDurationRange) {
        return BarberServiceSearchCriteria.builder()
                .search(search)
                .durationTypeCode(durationTypeCode)
                .branchId(branchId)
                .categoryId(categoryId)
                .priceRange(ValidateRange.validate(priceRange))
                .estimateDurationRange(ValidateRange.validate(estimateDurationRange))
                .build();
    }

    public Long priceFrom() {
        if(Objects.isNull(priceRange) || priceRange.isEmpty()) {
            return 0L;
        }

        return priceRange.getFirst();
    }

    public Long priceTo() {
        if(Objects.isNull(priceRange) || priceRange.isEmpty()) {
            return 0L;
        }

        return priceRange.getLast();
    }

    public Integer estimateDurationFrom() {
        if(Objects.isNull(estimateDurationRange) || estimateDurationRange.isEmpty()) {
            return 0;
        }

        return estimateDurationRange.getFirst();
    }

    public Integer estimateDurationTo() {
        if(Objects.isNull(estimateDurationRange) || estimateDurationRange.isEmpty()) {
            return 0;
        }

        return estimateDurationRange.getLast();
    }

    public Boolean hasSearchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean hasDurationTypeCodeEmpty() {
        return !StringUtils.hasText(durationTypeCode);
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasCategoryIdEmpty() {
        return Objects.isNull(categoryId);
    }

    public Boolean hasPriceRangeEmpty() {
        return Objects.isNull(priceRange) || priceRange.isEmpty();
    }

    public Boolean hasEstimateDurationRangeEmpty() {
        return Objects.isNull(estimateDurationRange) || estimateDurationRange.isEmpty();
    }
}
