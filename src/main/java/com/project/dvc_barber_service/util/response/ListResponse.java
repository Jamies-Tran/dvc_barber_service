package com.project.dvc_barber_service.util.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ListResponse<T>(List<T> data, Integer statusCode, String status, String message) {
    public static <T> ListResponse success(List<T> data, String message) {
        return ListResponse.builder()
                .data((List<Object>) data)
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .build();
    }

    public static ListResponse handler(String message, HttpStatus httpStatus) {
        return ListResponse.builder()
                .data(null)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .build();
    }
}
