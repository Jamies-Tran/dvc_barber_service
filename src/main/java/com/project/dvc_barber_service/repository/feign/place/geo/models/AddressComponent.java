package com.project.dvc_barber_service.repository.feign.place.geo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressComponent(
        @JsonProperty("short_name") String shortName
) {
    public String shortName() {
        return shortName.replaceAll("\\s", "");
    }
}
