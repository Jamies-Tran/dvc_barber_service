package com.project.dvc_barber_service.repository.feign.place.geo.models;

import java.util.List;

public record Address(
        List<AddressResult> results
) {
}
