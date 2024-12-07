package com.project.dvc_barber_service.dto.branch.action;

import com.project.dvc_barber_service.dto.branch.Branch;
import lombok.Builder;

@Builder
public record BranchCreateAction(
        Branch branch
) {
    public static BranchCreateAction buildFrom(Branch branch) {
        return BranchCreateAction.builder()
                .branch(branch)
                .build();
    }
}
