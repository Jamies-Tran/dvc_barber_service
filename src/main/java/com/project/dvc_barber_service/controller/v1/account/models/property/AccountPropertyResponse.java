package com.project.dvc_barber_service.controller.v1.account.models.property;

import lombok.Builder;

@Builder
public record AccountPropertyResponse(
        Long accountPropertyId,
        Long branchId,
        String expertiseCode,
        String expertiseName,
        String accountCode
) {
}
