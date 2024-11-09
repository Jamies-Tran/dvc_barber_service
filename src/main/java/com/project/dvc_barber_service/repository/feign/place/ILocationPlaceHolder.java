package com.project.dvc_barber_service.repository.feign.place;

import com.project.dvc_barber_service.repository.feign.place.models.location.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LocationPlaceHolder", url = "${app.domain.goong.host}")
@Import(FeignClientsConfiguration.class)
public interface ILocationPlaceHolder {
    @GetMapping("${app.domain.goong.places}")
    LocationResponse getLocationList(
            @RequestParam(required = false, value = "api_key", defaultValue = "") String apiKey,
            @RequestParam(required = false, value = "input", defaultValue = "") String search,
            @RequestParam(required = false, value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false, value = "radius", defaultValue = "30") Integer radius,
            @RequestParam(required = false, value = "more_compound", defaultValue = "false") Boolean moreCompound
    );
}
