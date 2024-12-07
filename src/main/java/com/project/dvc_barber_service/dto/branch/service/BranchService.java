package com.project.dvc_barber_service.dto.branch.service;

import lombok.With;

public record BranchService(
        Long branchServiceId,
        @With Long branchId,
        Long serviceId,
        Long price,
        String expertiseCode
) {
}
