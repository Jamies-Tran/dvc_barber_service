package com.project.dvc_barber_service.controller.v1.expertise.models;

import lombok.Builder;

@Builder
public record ExpertiseUpdateRequest(
        String expertiseName
) {
}
