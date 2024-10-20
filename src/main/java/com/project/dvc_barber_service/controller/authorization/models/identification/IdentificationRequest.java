package com.project.dvc_barber_service.controller.authorization.models.identification;

import lombok.Builder;

@Builder
public record IdentificationRequest(
        String phone,
        String password
) {
    public static IdentificationRequest buildFrom(String phone, String password) {
        return IdentificationRequest.builder()
                .phone(phone)
                .password(password)
                .build();
    }
}
