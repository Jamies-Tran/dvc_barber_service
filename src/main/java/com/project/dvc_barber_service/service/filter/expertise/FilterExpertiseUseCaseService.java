package com.project.dvc_barber_service.service.filter.expertise;

import com.project.dvc_barber_service.enums.expertise.EExpertise;
import com.project.dvc_barber_service.enums.expertise.action.FilterExpertiseFindByNameAction;
import com.project.dvc_barber_service.service.filter.expertise.usecase.IFilterExpertiseUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterExpertiseUseCaseService implements IFilterExpertiseUseCase {
    @NonNull FilterExpertiseQueryService queryService;

    @Override
    public List<EExpertise> findAllByName(FilterExpertiseFindByNameAction action) {
        return queryService.findAllByName(action);
    }
}
