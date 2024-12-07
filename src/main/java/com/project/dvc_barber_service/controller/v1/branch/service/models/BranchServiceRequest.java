package com.project.dvc_barber_service.controller.v1.branch.service.models;

public record BranchServiceRequest(
        Long serviceId,
        Long price,
        String expertiseCode
) {
}
