package com.project.dvc_barber_service.service.filter.location.usecase;

import com.project.dvc_barber_service.dto.location.LocationSearchCriteria;
import com.project.dvc_barber_service.repository.feign.place.location.models.LocationResponse;

public interface IFilterLocationUseCase {
    LocationResponse findAll(LocationSearchCriteria searchCriteria);
}
