package com.project.dvc_barber_service.controller.v2.account.models;

public record AccountRequest(
        String firstName,
        String lastName,
        String address,
        String phone
) {
}
