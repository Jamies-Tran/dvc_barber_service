package com.project.dvc_barber_service.service.auth.role.permission;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.permission.IRolePermissionMapper;
import com.project.dvc_barber_service.dto.auth.role.permission.RolePermission;
import com.project.dvc_barber_service.dto.auth.role.permission.action.RolePermissionCreateListAction;
import com.project.dvc_barber_service.repository.database.auth.role.permission.IRolePermissionRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RolePermissionCommandService {
    @NonNull IRolePermissionRepository repository;

    @NonNull IRolePermissionMapper mapper;

    public void saveAllOrDelete(RolePermissionCreateListAction createListAction) {
        Role role = createListAction.role();
        List<Permission> permissions = createListAction.permissions();
        List<RolePermission> rolePermissions = repository.findAllByRoleCode(role.roleCode())
                .stream()
                .map(mapper::toDto)
                .toList();
        for (RolePermission rolePermission : rolePermissions) {
            permissions.stream()
                    .filter(x -> Objects.equals(x.permissionCode(), rolePermission.permissionCode()))
                    .findAny()
                    .ifPresentOrElse(x -> {}, () -> {
                        repository.deleteByRoleCodeAndPermissionCode(rolePermission.roleCode(), rolePermission.permissionCode());
                        log.info("Đã xóa phân quyền:[{}] [{}]", rolePermission.roleCode(), rolePermission.permissionCode());
                    });
        }

        for (Permission permission : permissions) {
            rolePermissions.stream()
                    .filter(x -> Objects.equals(x.permissionCode(), permission.permissionCode()))
                    .findAny()
                    .ifPresentOrElse(x -> {}, () -> {
                        RolePermission rolePermission = RolePermission
                                .buildFrom(role.roleId(), permission.permissionId(), role.roleCode(), permission.permissionCode());
                        repository.save(mapper.toEntity(rolePermission));
                    });
        }
    }
}
