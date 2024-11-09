package com.project.dvc_barber_service.dto.account.action;

import com.project.dvc_barber_service.dto.account.Account;
import lombok.Builder;

@Builder
public record AccountUpdateAction(
        Long accountId,
        Account account
) {
    public static AccountUpdateAction buildFrom(Long accountId, Account account) {
        return AccountUpdateAction.builder()
                .accountId(accountId)
                .account(account)
                .build();
    }

    public static AccountUpdateAction buildFrom(Account account) {
        return AccountUpdateAction.builder()
                .account(account)
                .build();
    }
}
