package com.project.dvc_barber_service.dto.account.action;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.enums.role.ERole;
import lombok.Builder;

@Builder
public record AccountCreateAction(
        Account account,
        Long branchId,
        ERole role
) {
    public static AccountCreateAction buildFrom(Account account, Long branchId, ERole role) {
        return AccountCreateAction.builder()
                .account(account)
                .branchId(branchId)
                .role(role)
                .build();
    }

    public static AccountCreateAction buildFrom(Account account, ERole role) {
        return AccountCreateAction.builder()
                .account(account)
                .role(role)
                .build();
    }
}
