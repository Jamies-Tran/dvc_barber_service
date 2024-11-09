package com.project.dvc_barber_service.controller.v2.account.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AccountUpdateRequest(
        String firstName,
        String lastName,
        String address,
        String phone,
        LocalDateTime dob,
        String avatar
) {
}
