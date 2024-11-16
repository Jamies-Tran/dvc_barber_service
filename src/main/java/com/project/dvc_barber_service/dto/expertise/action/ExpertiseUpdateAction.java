package com.project.dvc_barber_service.dto.expertise.action;

import com.project.dvc_barber_service.dto.expertise.Expertise;
import lombok.Builder;

@Builder
public record ExpertiseUpdateAction(
        Long expertiseId,
        Expertise expertise
) {
    public static ExpertiseUpdateAction buildFrom(Long expertiseId, Expertise expertise) {
        return ExpertiseUpdateAction.builder()
                .expertiseId(expertiseId)
                .expertise(expertise)
                .build();
    }
}
