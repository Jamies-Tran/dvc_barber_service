package com.project.dvc_barber_service.dto.account.action;

import lombok.Builder;

@Builder
public record AccountFindByIdAction(
        Long accountId
) {
    public static AccountFindByIdAction buildFrom(Long accountId) {
        return AccountFindByIdAction.builder()
                .accountId(accountId)
                .build();
    }
}
