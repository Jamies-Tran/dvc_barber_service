package com.project.dvc_barber_service.controller.v3.filter.status.account.models;

import com.project.dvc_barber_service.enums.status.account.EAccountStatus;
import lombok.Builder;

@Builder
public record AccountStatusResponse(
        String code,
        String name
) {
    public static AccountStatusResponse buildFrom(EAccountStatus accountStatus) {
        return AccountStatusResponse.builder()
                .code(accountStatus.getCode())
                .name(accountStatus.getName())
                .build();
    }
}
