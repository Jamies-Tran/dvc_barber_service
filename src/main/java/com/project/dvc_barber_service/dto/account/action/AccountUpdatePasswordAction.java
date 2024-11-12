package com.project.dvc_barber_service.dto.account.action;

import lombok.Builder;

@Builder
public record AccountUpdatePasswordAction(
        String oldPassword,
        String newPassword
) {
    public static AccountUpdatePasswordAction buildFrom(String oldPassword, String newPassword) {
        return AccountUpdatePasswordAction.builder()
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
    }
}
