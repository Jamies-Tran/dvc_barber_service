package com.project.dvc_barber_service.dto.branch;

import com.project.dvc_barber_service.dto.branch.service.BranchService;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Builder
public record Branch(
        Long branchId,
        String branchCode,
        String contact,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalTime openTime,
        LocalTime closeTime,
        Integer capacity,
        @With List<BranchService> branchServices,
        String statusCode,
        String statusName
) {
}
