package com.project.dvc_barber_service.service.filter.location;

import com.project.dvc_barber_service.dto.location.LocationSearchCriteria;
import com.project.dvc_barber_service.repository.feign.place.models.location.LocationResponse;
import com.project.dvc_barber_service.service.filter.location.usecase.IFilterLocationUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterLocationUseCaseService implements IFilterLocationUseCase {
    @NonNull FilterLocationQueryService queryService;

    @Override
    public LocationResponse findAll(LocationSearchCriteria searchCriteria) {
        return queryService.findAll(searchCriteria);
    }
}
