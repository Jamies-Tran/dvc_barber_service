package com.project.dvc_barber_service.dto.auth.identification.action;

import lombok.Builder;

@Builder
public record VerifyIdentificationAction(
        String phone,
        String password
) {
    public static VerifyIdentificationAction buildFrom(String phone, String password) {
        return VerifyIdentificationAction.builder()
                .phone(phone)
                .password(password)
                .build();
    }
}
