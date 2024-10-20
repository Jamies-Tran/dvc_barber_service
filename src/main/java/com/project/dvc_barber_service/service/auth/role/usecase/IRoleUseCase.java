package com.project.dvc_barber_service.service.auth.role.usecase;

import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateListAction;

import java.util.List;

public interface IRoleUseCase {
    List<Role> save(RoleCreateListAction createListAction);
}
