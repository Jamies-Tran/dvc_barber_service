package com.project.dvc_barber_service.dto.auth.identification;

import com.project.dvc_barber_service.dto.auth.role.Role;
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
        String phone,
        Role role
) {
}
