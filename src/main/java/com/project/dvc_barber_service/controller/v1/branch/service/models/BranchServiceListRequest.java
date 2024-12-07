package com.project.dvc_barber_service.controller.v1.branch.service.models;

import java.util.List;

public record BranchServiceListRequest(
        List<BranchServiceRequest> branchServices
) {
}
