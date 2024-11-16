package com.project.dvc_barber_service.dto.expertise.action;

import lombok.Builder;

@Builder
public record ExpertiseFindByIdAction(
        Long expertiseId
) {
    public static ExpertiseFindByIdAction buildFrom(Long expertiseId) {
        return ExpertiseFindByIdAction.builder()
                .expertiseId(expertiseId)
                .build();
    }
}
