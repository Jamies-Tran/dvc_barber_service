package com.project.dvc_barber_service.repository.feign.place.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Location(
        @JsonProperty("description") String locationName
) {
}
