package com.project.dvc_barber_service.dto.expertise;

import lombok.Builder;
import lombok.With;

@Builder
public record Expertise(
        Long expertiseId,
        @With String expertiseCode,
        String expertiseName,
        String statusCode,
        String statusName
) {
}
