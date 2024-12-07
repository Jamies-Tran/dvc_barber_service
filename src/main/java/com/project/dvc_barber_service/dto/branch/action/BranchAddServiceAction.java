package com.project.dvc_barber_service.dto.branch.action;

import com.project.dvc_barber_service.dto.branch.service.BranchService;
import lombok.Builder;

import java.util.List;

@Builder
public record BranchAddServiceAction(
        Long branchId,
        List<BranchService> branchServices
) {
    public static BranchAddServiceAction buildFrom(Long branchId,
                                                   List<BranchService> servicePrices) {


        return BranchAddServiceAction.builder()
                .branchId(branchId)
                .branchServices(addBranchId(branchId, servicePrices))
                .build();
    }

    private static List<BranchService> addBranchId(Long branchId,
                                                   List<BranchService> servicePrices) {
        return servicePrices.stream()
                .map(x -> x.withBranchId(branchId))
                .toList();
    }
}
