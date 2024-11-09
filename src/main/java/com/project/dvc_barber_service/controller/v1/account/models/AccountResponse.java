package com.project.dvc_barber_service.controller.v1.account.models;

import com.project.dvc_barber_service.controller.v1.account.models.role.AccountRoleResponse;
import com.project.dvc_barber_service.util.response.MediaResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountResponse(
        Long accountId,
        String accountCode,
        String firstName,
        String lastName,
        LocalDateTime dob,
        String address,
        String phone,
        String avatar,
        List<MediaResponse> openingImageMedia,
        String expertiseCode,
        String expertiseName,
        String statusCode,
        String statusName,
        AccountRoleResponse role
) {
}
