package com.project.dvc_barber_service.service.auth.permission;

import com.project.dvc_barber_service.dto.auth.permission.IPermissionMapper;
import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.permission.action.PermissionFindByRoleIdAction;
import com.project.dvc_barber_service.repository.auth.permission.IPermissionRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionQueryService {
    @NonNull IPermissionRepository repository;

    @NonNull IPermissionMapper mapper;

    public List<Permission> findByRoleId(PermissionFindByRoleIdAction action) {
        return repository.findByRoleId(action.roleId())
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
