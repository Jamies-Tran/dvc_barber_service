package com.project.dvc_barber_service.repository.feign.place.geo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record LocationGeometry(
        @JsonProperty("lat") BigDecimal latitude,
        @JsonProperty("lng") BigDecimal longitude
) {
}
