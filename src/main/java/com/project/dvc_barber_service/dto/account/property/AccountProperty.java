package com.project.dvc_barber_service.dto.account.property;

public record AccountProperty(
        Long accountPropertyId,
        Long branchId,
        String accountCode
) {
}
