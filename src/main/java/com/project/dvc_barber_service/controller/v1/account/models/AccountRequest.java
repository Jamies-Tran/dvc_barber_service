package com.project.dvc_barber_service.controller.v1.account.models;

public record AccountRequest(
        Long branchId,
        String firstName,
        String lastName,
        String address,
        String phone,
        String expertiseCode,
        String roleCode
) {
}
