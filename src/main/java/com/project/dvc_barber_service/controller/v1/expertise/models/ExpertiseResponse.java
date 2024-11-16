package com.project.dvc_barber_service.controller.v1.expertise.models;

import lombok.Builder;

@Builder
public record ExpertiseResponse(
        Long expertiseId,
        String expertiseCode,
        String expertiseName,
        String statusCode,
        String statusName
) {
}
