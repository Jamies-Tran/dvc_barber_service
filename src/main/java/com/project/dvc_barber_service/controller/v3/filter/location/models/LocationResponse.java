package com.project.dvc_barber_service.controller.v3.filter.location.models;

import lombok.Builder;

import java.util.List;

@Builder
public record LocationResponse(
        List<Location> locations
) {
}
