package com.project.dvc_barber_service.service.geocoding;

import com.project.dvc_barber_service.repository.feign.place.geo.IGeocodingPlaceHolder;
import com.project.dvc_barber_service.repository.feign.place.geo.models.Address;
import com.project.dvc_barber_service.repository.feign.place.geo.models.AddressResult;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ForwardGeocodingService {
    @NonNull IGeocodingPlaceHolder geocodingService;

    @NonFinal
    @Value("${app.key.goong}")
    String apiKey;

    public Address forwardGeocoding(String address) {
        try {
            return geocodingService.getAddressGeocoding(apiKey, address);
        } catch (Exception e) {
            log.error("[{}-forwardGeocoding] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}
