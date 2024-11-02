package com.project.dvc_barber_service.dto.account.property.action;

import com.project.dvc_barber_service.dto.account.property.AccountProperty;
import com.project.dvc_barber_service.enums.role.ERole;
import lombok.Builder;

@Builder
public record AccountPropCreateAction(
        AccountProperty accountProperty,
        ERole role
) {
    public static AccountPropCreateAction buildFrom(AccountProperty accountProperty, ERole role) {
        return AccountPropCreateAction.builder()
                .accountProperty(accountProperty)
                .role(role)
                .build();
    }
}
