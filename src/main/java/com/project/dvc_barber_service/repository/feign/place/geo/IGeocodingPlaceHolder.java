package com.project.dvc_barber_service.repository.feign.place.geo;

import com.project.dvc_barber_service.repository.feign.place.geo.models.Address;
import com.project.dvc_barber_service.repository.feign.place.geo.models.AddressResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Geocoding", url = "${app.domain.goong.host}")
@Import(FeignClientProperties.FeignClientConfiguration.class)
public interface IGeocodingPlaceHolder {
    @GetMapping("${app.domain.goong.geo}")
    Address getAddressGeocoding(
            @RequestParam(value = "api_key") String apiKey,
            @RequestParam(value = "address") String address
    );
}
