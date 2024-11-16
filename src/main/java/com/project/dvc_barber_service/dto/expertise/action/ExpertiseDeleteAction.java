package com.project.dvc_barber_service.dto.expertise.action;

import lombok.Builder;

@Builder
public record ExpertiseDeleteAction(
        Long expertiseId
) {
    public static ExpertiseDeleteAction buildFrom(Long expertiseId) {
        return ExpertiseDeleteAction.builder()
                .expertiseId(expertiseId)
                .build();
    }
}
