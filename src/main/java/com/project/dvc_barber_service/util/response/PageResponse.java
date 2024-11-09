package com.project.dvc_barber_service.util.response;

import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;


@Builder
public record PageResponse<T>(
        List<T> data,
        Long totalElement,
        Integer totalPages,
        Integer current,
        Integer pageSize,
        Integer statusCode,
        String status,
        String message
) {
    public static <T> PageResponse success(Page<T> data, String message) {
        return PageResponse.builder()
                .data((List<Object>) data.getContent())
                .totalElement(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .current(data.getPageable().getPageNumber())
                .pageSize(data.getPageable().getPageSize())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .build();
    }

    public static PageResponse handler(String message, HttpStatus httpStatus) {
        return PageResponse.builder()
                .data(null)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .build();
    }
}
