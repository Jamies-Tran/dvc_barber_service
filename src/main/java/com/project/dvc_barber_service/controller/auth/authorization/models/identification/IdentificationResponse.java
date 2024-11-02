package com.project.dvc_barber_service.controller.auth.authorization.models.identification;

import com.project.dvc_barber_service.controller.auth.authorization.models.RoleResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record IdentificationResponse(
    TokenResponse token,
    Long accountId,
    Long branchId,
    String accountCode,
    String firstName,
    String lastName,
    String phone,
    RoleResponse role
) {
}
