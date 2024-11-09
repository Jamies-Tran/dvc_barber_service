package com.project.dvc_barber_service.service.filter.status.account.usecase;

import com.project.dvc_barber_service.enums.status.EAccountStatus;
import com.project.dvc_barber_service.enums.status.action.FilterAccountStatusFindByNameAction;

import java.util.List;

public interface IFilterAccountStatusUseCase {
    List<EAccountStatus> findAllByName(FilterAccountStatusFindByNameAction action);
}
