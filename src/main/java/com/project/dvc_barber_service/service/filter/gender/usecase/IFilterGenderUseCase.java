package com.project.dvc_barber_service.service.filter.gender.usecase;

import com.project.dvc_barber_service.enums.gender.EGender;
import com.project.dvc_barber_service.enums.gender.action.FilterGenderAction;

import java.util.List;

public interface IFilterGenderUseCase {
    List<EGender> findAll(FilterGenderAction action);
}
