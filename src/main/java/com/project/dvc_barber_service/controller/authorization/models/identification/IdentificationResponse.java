package com.project.dvc_barber_service.controller.authorization.models.identification;

import lombok.Builder;

@Builder
public record IdentificationResponse(
    TokenResponse token,
    Long accountId,
    Long branchId,
    String accountCode,
    String firstName,
    String lastName,
    String phone
) {
}
