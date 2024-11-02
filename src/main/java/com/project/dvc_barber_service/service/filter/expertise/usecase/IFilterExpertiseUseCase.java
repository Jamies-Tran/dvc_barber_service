package com.project.dvc_barber_service.service.filter.expertise.usecase;

import com.project.dvc_barber_service.enums.expertise.EExpertise;
import com.project.dvc_barber_service.enums.expertise.action.FilterExpertiseFindByNameAction;

import java.util.List;

public interface IFilterExpertiseUseCase {
    List<EExpertise> findAllByName(FilterExpertiseFindByNameAction action);
}
