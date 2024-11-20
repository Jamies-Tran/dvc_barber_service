package com.project.dvc_barber_service.service.filter.gender;

import com.project.dvc_barber_service.enums.gender.EGender;
import com.project.dvc_barber_service.enums.gender.action.FilterGenderAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterGenderQueryService {
    public List<EGender> findAll(FilterGenderAction action) {
        return EGender.getList(action.name());
    }
}
