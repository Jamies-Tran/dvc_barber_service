package com.project.dvc_barber_service.dto.auth.identification;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Identification(
        Token token,
        Long accountId,
        Long branchId,
        String accountCode,
        String firstName,
        String lastName,
        String phone
) {
}
