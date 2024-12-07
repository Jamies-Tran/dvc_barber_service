package com.project.dvc_barber_service.controller.v3.filter.location.models;

import lombok.Builder;

import java.util.List;

@Builder
public record LocationResponse(
        List<Location> locations
) {
    public static LocationResponse buildFrom(com.project.dvc_barber_service.repository.feign.place.location.models.LocationResponse goongLocation) {
        List<Location> locations  = goongLocation.locations().stream()
                .map(x -> Location.buildFrom(x.locationName(), x.textFormat().mainText()))
                .toList();

        return LocationResponse.builder()
                .locations(locations)
                .build();
    }
}
