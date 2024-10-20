package com.project.dvc_barber_service.dto.account.action;

import lombok.Builder;

@Builder
public record AccountFindByPhoneAction(String phone) {
    public static AccountFindByPhoneAction buildFrom(String phone) {
        return AccountFindByPhoneAction.builder()
                .phone(phone)
                .build();
    }
}
