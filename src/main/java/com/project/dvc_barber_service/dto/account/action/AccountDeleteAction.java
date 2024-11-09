package com.project.dvc_barber_service.dto.account.action;

import lombok.Builder;

@Builder
public record AccountDeleteAction(Long accountId) {
    public static AccountDeleteAction buildFrom(Long accountId) {
        return AccountDeleteAction.builder()
                .accountId(accountId)
                .build();
    }
}
