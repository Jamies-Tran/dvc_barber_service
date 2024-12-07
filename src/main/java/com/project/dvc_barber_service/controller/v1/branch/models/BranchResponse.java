package com.project.dvc_barber_service.controller.v1.branch.models;

import com.project.dvc_barber_service.controller.v1.branch.service.models.BranchServiceResponse;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public record BranchResponse(
        Long branchId,
        String branchCode,
        String contact,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalTime openTime,
        LocalTime closeTime,
        Integer capacity,
        List<BranchServiceResponse> branchServices,
        String statusCode,
        String statusName
) {
}
