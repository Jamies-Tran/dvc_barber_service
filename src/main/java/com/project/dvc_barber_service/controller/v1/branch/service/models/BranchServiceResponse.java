package com.project.dvc_barber_service.controller.v1.branch.service.models;


public record BranchServiceResponse(
        Long branchServiceId,
        Long branchId,
        Long serviceId,
        Long price,
        String expertiseCode
) {
}
