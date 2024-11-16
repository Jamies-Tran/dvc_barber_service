package com.project.dvc_barber_service.dto.expertise.action;

import com.project.dvc_barber_service.dto.expertise.Expertise;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ExpertiseCreateAction(Expertise expertise) {
    public static ExpertiseCreateAction buildFrom(Expertise expertise) {
        String expertiseCode = UUID.randomUUID().toString();
        return ExpertiseCreateAction.builder()
                .expertise(expertise.withExpertiseCode(expertiseCode))
                .build();
    }
}
