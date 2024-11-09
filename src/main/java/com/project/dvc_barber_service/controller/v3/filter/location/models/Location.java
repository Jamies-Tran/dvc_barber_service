package com.project.dvc_barber_service.controller.v3.filter.location.models;

import lombok.Builder;

@Builder
public record Location(
        String locationName
) {
}
