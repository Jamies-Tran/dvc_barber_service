package com.project.dvc_barber_service.service.filter.status.account;

import com.project.dvc_barber_service.enums.status.EAccountStatus;
import com.project.dvc_barber_service.enums.status.action.FilterAccountStatusFindByNameAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterAccountStatusQueryService {
    public List<EAccountStatus> findAllByName(FilterAccountStatusFindByNameAction action) {
        return EAccountStatus.getList(action.name());
    }
}
