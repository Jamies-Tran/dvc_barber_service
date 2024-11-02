package com.project.dvc_barber_service.service.filter.role.usecase;

import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.enums.role.action.FilterRoleFindByNameAction;

import java.util.List;

public interface IFilterRoleUseCase {
    List<ERole> findAllByName(FilterRoleFindByNameAction action);
}
