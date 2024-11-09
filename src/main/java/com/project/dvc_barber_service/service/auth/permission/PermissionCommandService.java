package com.project.dvc_barber_service.service.auth.permission;

import com.project.dvc_barber_service.dto.auth.permission.IPermissionMapper;
import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.permission.action.PermissionCreateListAction;
import com.project.dvc_barber_service.repository.database.auth.permission.IPermissionRepository;
import com.project.dvc_barber_service.repository.database.auth.permission.PermissionEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionCommandService {
    @NonNull IPermissionRepository repository;

    @NonNull IPermissionMapper mapper;

    public List<Permission> saveAll(PermissionCreateListAction createListAction) {
        List<Permission> permissions = createListAction.permissions();
        List<Permission> newPermissions = new ArrayList<>();
        for (Permission permission : permissions) {
            repository.findByPermissionCode(permission.permissionCode())
                    .ifPresentOrElse(
                            x -> {
                                log.info("[{}] Permission đã tồn tại", x.getPermissionCode());
                                newPermissions.add(mapper.toDto(x));
                            },
                            () -> {
                                PermissionEntity newPermission = repository.save(mapper.toEntity(permission));
                                newPermissions.add(mapper.toDto(newPermission));
                            });
        }

        return newPermissions;
    }
}
