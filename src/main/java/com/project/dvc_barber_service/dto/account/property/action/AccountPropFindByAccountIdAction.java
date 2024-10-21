package com.project.dvc_barber_service.dto.account.property.action;

import lombok.Builder;

@Builder
public record AccountPropFindByAccountIdAction(Long accountId) {
    public static AccountPropFindByAccountIdAction buildFrom(Long accountId) {
        return AccountPropFindByAccountIdAction.builder()
                .accountId(accountId)
                .build();
    }
}
