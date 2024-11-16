package com.project.dvc_barber_service.service.filter.status.account;

import com.project.dvc_barber_service.enums.status.account.EAccountStatus;
import com.project.dvc_barber_service.enums.status.account.action.FilterAccountStatusFindByNameAction;
import com.project.dvc_barber_service.service.filter.status.account.usecase.IFilterAccountStatusUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterAccountStatusUseCaseService implements IFilterAccountStatusUseCase {
    @NonNull FilterAccountStatusQueryService queryService;

    @Override
    public List<EAccountStatus> findAllByName(FilterAccountStatusFindByNameAction action) {
        return queryService.findAllByName(action);
    }
}
