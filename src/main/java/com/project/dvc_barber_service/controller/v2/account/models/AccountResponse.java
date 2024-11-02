package com.project.dvc_barber_service.controller.v2.account.models;

import com.project.dvc_barber_service.controller.v2.account.models.property.AccountPropertyResponse;
import com.project.dvc_barber_service.controller.v2.account.models.role.AccountRoleResponse;
import lombok.Builder;

@Builder
public record AccountResponse(
        Long accountId,
        String firstName,
        String lastName,
        String address,
        String phone,
        String statusCode,
        String statusName,
        AccountRoleResponse role,
        AccountPropertyResponse accountProperty
) {
}
