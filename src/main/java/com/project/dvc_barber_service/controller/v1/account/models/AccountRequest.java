package com.project.dvc_barber_service.controller.v1.account.models;

import java.time.LocalDateTime;

public record AccountRequest(
        Long branchId,
        String firstName,
        String lastName,
        LocalDateTime dob,
        String address,
        String phone,
        String avatar,
        String expertiseCode,
        String roleCode
) {
}
