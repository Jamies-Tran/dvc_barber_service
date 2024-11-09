package com.project.dvc_barber_service.controller.v2.account.models;

import java.time.LocalDateTime;

public record AccountRequest(
        String firstName,
        String lastName,
        LocalDateTime dob,
        String address,
        String phone,
        String avatar
) {
}
