package com.project.dvc_barber_service.dto.account.property;

import com.project.dvc_barber_service.enums.expertise.EExpertise;
import lombok.Builder;

@Builder
public record AccountProperty(
        Long accountPropertyId,
        Long accountId,
        Long branchId,
        String expertiseCode,
        String expertiseName,
        String accountCode
) {
    public static AccountProperty buildFrom(Long branchId, Long accountId, String accountCode) {
        return AccountProperty.builder()
                .branchId(branchId)
                .accountId(accountId)
                .accountCode(accountCode)
                .build();
    }

    public static AccountProperty buildFrom(Long branchId, Long accountId, String accountCode, EExpertise expertise) {
        return AccountProperty.builder()
                .branchId(branchId)
                .accountId(accountId)
                .accountCode(accountCode)
                .expertiseCode(expertise.getCode())
                .expertiseName(expertise.getName())
                .build();
    }

    public static AccountProperty buildFrom(Long accountId, String accountCode) {
        return AccountProperty.builder()
                .accountId(accountId)
                .accountCode(accountCode)
                .build();
    }

    public static AccountProperty empty(Long accountId) {
        return AccountProperty.builder()
                .accountId(accountId)
                .build();
    }
}
