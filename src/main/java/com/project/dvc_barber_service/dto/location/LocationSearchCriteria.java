package com.project.dvc_barber_service.dto.location;

import lombok.Builder;
import org.springframework.web.bind.annotation.RequestParam;

@Builder
public record LocationSearchCriteria(
        String search,
        Integer limit,
        Integer radius,
        Boolean moreCompound
) {
    public static LocationSearchCriteria buildFrom(String search,
                                                   Integer limit,
                                                   Integer radius,
                                                   Boolean moreCompound) {
        return LocationSearchCriteria.builder()
                .search(search)
                .limit(limit)
                .radius(radius)
                .moreCompound(moreCompound)
                .build();
    }
}
