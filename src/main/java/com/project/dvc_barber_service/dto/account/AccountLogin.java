package com.project.dvc_barber_service.dto.account;

import lombok.Builder;

@Builder
public record AccountLogin(
        Long accountId,
        Long branchId,
        String phone,
        String fullName,
        String roleCode,
        String accountCode
) {
}
