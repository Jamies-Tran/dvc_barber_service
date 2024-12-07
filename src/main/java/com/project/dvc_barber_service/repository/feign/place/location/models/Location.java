package com.project.dvc_barber_service.repository.feign.place.location.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Location(
        @JsonProperty("description") String locationName,
        @JsonProperty("structured_formatting") LocationTextFormat textFormat
) {
}
