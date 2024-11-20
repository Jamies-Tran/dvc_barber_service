package com.project.dvc_barber_service.service.filter.gender;

import com.project.dvc_barber_service.enums.gender.EGender;
import com.project.dvc_barber_service.enums.gender.action.FilterGenderAction;
import com.project.dvc_barber_service.service.filter.gender.usecase.IFilterGenderUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterGenderUseCaseService implements IFilterGenderUseCase {
    @NonNull FilterGenderQueryService queryService;

    @Override
    public List<EGender> findAll(FilterGenderAction action) {
        return queryService.findAll(action);
    }
}
