package com.project.dvc_barber_service.service.auth.role;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.permission.action.PermissionCreateListAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateAction;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateListAction;
import com.project.dvc_barber_service.dto.auth.role.permission.RolePermission;
import com.project.dvc_barber_service.dto.auth.role.permission.action.RolePermissionCreateListAction;
import com.project.dvc_barber_service.enums.permission.EPermission;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.repository.auth.role.RoleEntity;
import com.project.dvc_barber_service.service.cache.MyCacheCommandService;
import com.project.dvc_barber_service.service.auth.permission.PermissionCommandService;
import com.project.dvc_barber_service.service.auth.role.permission.RolePermissionCommandService;
import com.project.dvc_barber_service.service.auth.role.usecase.IRoleUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleUseCaseService implements IRoleUseCase {
    @NonNull RoleCommandService command;

    @NonNull PermissionCommandService permissionCommand;

    @NonNull RolePermissionCommandService rolePermissionCommand;

    /*1-Tạo phân quyền*/
    @Transactional
    @Override
    public void save(RoleCreateListAction createListAction) {
        List<Role> roles = ERole.getList()
                .stream()
                .map(Role::buildFrom)
                .toList();
        List<Permission> permissions = EPermission.getList()
                .stream()
                .map(Permission::buildFrom)
                .toList();
        List<Role> savedRoles = command.saveAll(RoleCreateListAction.buildFrom(roles));
        List<Permission> savedPermissions = permissionCommand.saveAll(PermissionCreateListAction.buildFrom(permissions));
        for (Role role : savedRoles) {
            List<Permission> rolePermissions = createListAction.roles().stream()
                    .filter(x -> Objects.equals(x.roleCode(), role.roleCode()))
                    .map(Role::permissions)
                    .findAny().orElse(new ArrayList<>());

            rolePermissionCommand.saveAllOrDelete(RolePermissionCreateListAction.buildFrom(
                    role,
                    savedPermissions.stream()
                            .filter(x -> rolePermissions.stream().map(Permission::permissionCode)
                                    .toList().contains(x.permissionCode())).toList()));
        }
    }
    /*1-end*/
}
