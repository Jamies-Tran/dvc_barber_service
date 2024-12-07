package com.project.dvc_barber_service.dto.branch.action;

import lombok.Builder;

@Builder
public record BranchFindByIdAction(Long branchId) {
    public static BranchFindByIdAction buildFrom(Long branchId) {
        return BranchFindByIdAction.builder()
                .branchId(branchId)
                .build();
    }
}
