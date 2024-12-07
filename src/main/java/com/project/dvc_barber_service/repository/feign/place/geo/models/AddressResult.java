package com.project.dvc_barber_service.repository.feign.place.geo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AddressResult(
        @JsonProperty("formatted_address") String addressDetail,
        @JsonProperty("address_components") List<AddressComponent> addressComponents,
        Geometry geometry
) {
}
