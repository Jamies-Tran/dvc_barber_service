package com.project.dvc_barber_service.controller.v2.account.models;

import com.project.dvc_barber_service.controller.v2.account.models.role.AccountRoleResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AccountV2Response(
        Long accountId,
        String firstName,
        String lastName,
        String address,
        String phone,
        LocalDateTime dob,
        String avatar,
        String statusCode,
        String statusName,
        AccountRoleResponse role
) {
}
