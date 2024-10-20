package com.project.dvc_barber_service.service.auth.permission;

import com.project.dvc_barber_service.dto.auth.permission.IPermissionMapper;
import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.permission.action.PermissionCreateListAction;
import com.project.dvc_barber_service.repository.auth.permission.IPermissionRepository;
import com.project.dvc_barber_service.repository.auth.permission.PermissionEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionCommandService {
    @NonNull IPermissionRepository repository;

    @NonNull IPermissionMapper mapper;

    public List<Permission> saveAll(PermissionCreateListAction createListAction) {
        List<PermissionEntity> newPermissions = createListAction.permissions()
                .stream()
                .map(mapper::toEntity)
                .toList();

        return repository.saveAll(newPermissions)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
