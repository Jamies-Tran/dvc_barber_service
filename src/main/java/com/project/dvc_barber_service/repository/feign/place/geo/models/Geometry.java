package com.project.dvc_barber_service.repository.feign.place.geo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Geometry(
    @JsonProperty("location") LocationGeometry locationGeometry
) {
}
