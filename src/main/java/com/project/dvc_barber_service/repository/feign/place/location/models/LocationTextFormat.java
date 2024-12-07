package com.project.dvc_barber_service.repository.feign.place.location.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocationTextFormat(
        @JsonProperty("main_text") String mainText
) {
}
