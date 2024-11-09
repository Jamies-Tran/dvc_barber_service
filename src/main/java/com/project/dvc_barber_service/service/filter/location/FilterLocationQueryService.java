package com.project.dvc_barber_service.service.filter.location;

import com.project.dvc_barber_service.dto.location.LocationSearchCriteria;
import com.project.dvc_barber_service.repository.feign.place.ILocationPlaceHolder;
import com.project.dvc_barber_service.repository.feign.place.models.location.LocationResponse;
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
public class FilterLocationQueryService {
    @NonNull ILocationPlaceHolder placeHolder;

    @NonFinal
    @Value("${app.key.goong}")
    String apiKey;

    public LocationResponse findAll(LocationSearchCriteria searchCriteria) {
        try {
            return placeHolder.getLocationList(
                    apiKey,
                    searchCriteria.search(),
                    searchCriteria.limit(),
                    searchCriteria.radius(),
                    searchCriteria.moreCompound());
        } catch (Exception e) {
            log.error("[{}-findAll] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}
