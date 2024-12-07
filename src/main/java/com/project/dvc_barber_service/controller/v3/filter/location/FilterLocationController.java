package com.project.dvc_barber_service.controller.v3.filter.location;

import com.project.dvc_barber_service.controller.v3.filter.location.models.ILocationModelMapper;
import com.project.dvc_barber_service.controller.v3.filter.location.models.LocationResponse;
import com.project.dvc_barber_service.dto.location.LocationSearchCriteria;
import com.project.dvc_barber_service.service.filter.location.usecase.IFilterLocationUseCase;
import com.project.dvc_barber_service.util.response.ListResponse;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterLocationController implements IFilterLocationAPI {
    @NonNull IFilterLocationUseCase useCase;

    @NonNull ILocationModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(String search,
                                     Integer limit,
                                     Integer radius,
                                     Boolean moreCompound) {
        try {
            LocationSearchCriteria searchCriteria = LocationSearchCriteria.buildFrom(
                    search, limit, radius, moreCompound);
            com.project.dvc_barber_service.repository.feign.place.location.models.LocationResponse location = useCase
                    .findAll(searchCriteria);
            LocationResponse response = LocationResponse.buildFrom(location);

            return ResponseEntity.ok(ListResponse.success(response.locations(),
                    "Gợi ý danh sách địa điểm"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
