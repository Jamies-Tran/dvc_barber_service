package com.project.dvc_barber_service.util.request;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Builder
public record PageRequestCustom(PageRequest pageRequest) {
    public static PageRequestCustom buildFrom(int current, int pageSize) {
        return PageRequestCustom.builder()
                .pageRequest(PageRequest.of(current - 1, pageSize))
                .build();
    }

    public static PageRequestCustom buildFrom(int current, int pageSize, String sorter) {

        return PageRequestCustom.builder()
                .pageRequest(PageRequest.of(current - 1, pageSize, customSort(sorter)))
                .build();
    }

    private static Sort customSort(String sorter) {
        if (StringUtils.hasText(sorter)) {
            String[] sorters = sorter.split("_");
            if(sorters.length == 1) {
                return Sort.by(Sort.Direction.DESC, sorters[0]);
            } else {
                if(Objects.equals(sorters[1], "asc")) {
                    return Sort.by(Sort.Direction.ASC, sorters[0]);
                } else {
                    return Sort.by(Sort.Direction.DESC, sorters[0]);
                }
            }
        } else {
            return Sort.by(Sort.Direction.DESC, "createdAt");
        }
    }
}
