package com.project.dvc_barber_service.repository.feign.place.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record LocationResponse(
        @JsonProperty("predictions") List<Location> locations
) {
}
