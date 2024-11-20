package com.project.dvc_barber_service.controller.v2.account.models;

import java.time.LocalDateTime;

public record AccountV2Request(
        String firstName,
        String lastName,
        String genderCode,
        String password,
        LocalDateTime dob,
        String address,
        String phone,
        String avatar
) {
}
