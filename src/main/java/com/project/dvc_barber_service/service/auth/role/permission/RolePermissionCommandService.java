package com.project.dvc_barber_service.service.auth.role.permission;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.permission.IRolePermissionMapper;
import com.project.dvc_barber_service.dto.auth.role.permission.RolePermission;
import com.project.dvc_barber_service.dto.auth.role.permission.action.RolePermissionCreateListAction;
import com.project.dvc_barber_service.repository.auth.role.permission.IRolePermissionRepository;
import com.project.dvc_barber_service.repository.auth.role.permission.RolePermissionEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RolePermissionCommandService {
    @NonNull IRolePermissionRepository repository;

    @NonNull IRolePermissionMapper mapper;

    public List<RolePermission> saveAll(RolePermissionCreateListAction createListAction) {
        List<Permission> permissions = createListAction.permissions();
        List<RolePermissionEntity> newRolePermissions = permissions.stream()
                .map(permission -> RolePermission.buildFrom(createListAction.roleId(),
                        permission.permissionId()))
                .map(mapper::toEntity)
                .toList();

        Map<Long, Permission> permissionMap = permissions.stream()
                .collect(Collectors.toMap(Permission::permissionId, p -> p));
        return repository.saveAll(newRolePermissions)
                .stream()
                .map(permission -> mapper.toDto(permission)
                        .withPermission(permissionMap.computeIfAbsent(permission.getPermissionId(), xx -> Permission.empty())))
                .toList();
    }
}
