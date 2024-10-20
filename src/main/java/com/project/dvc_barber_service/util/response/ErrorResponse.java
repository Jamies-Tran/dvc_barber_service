package com.project.dvc_barber_service.util.response;


import lombok.Builder;

@Builder
public record ErrorResponse(
        Integer statusCode,
        String status,
        String message
) {
    public static ErrorResponse of(Integer statusCode, String status, String message) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .status(status)
                .message(message)
                .build();
    }
}
