package com.project.dvc_barber_service.util.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ValueResponse<T>(T data, Integer statusCode, String status, String message) {
    public static ValueResponse success(Object data, String message) {
        return ValueResponse.builder()
                .data(data)
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .message(message)
                .build();
    }

    public static ValueResponse handler(String message, HttpStatus httpStatus) {
        return ValueResponse.builder()
                .data(null)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .build();
    }
}
