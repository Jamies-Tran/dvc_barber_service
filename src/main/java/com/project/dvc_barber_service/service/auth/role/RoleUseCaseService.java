package com.project.dvc_barber_service.service.auth.role;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.permission.action.PermissionCreateListAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateAction;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateListAction;
import com.project.dvc_barber_service.dto.auth.role.permission.RolePermission;
import com.project.dvc_barber_service.dto.auth.role.permission.action.RolePermissionCreateListAction;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleUseCaseService implements IRoleUseCase {
    @NonNull RoleCommandService command;

    @NonNull PermissionCommandService permissionCommand;

    @NonNull RolePermissionCommandService rolePermissionCommand;

    @NonNull MyCacheCommandService cacheCommandService;

    @Transactional
    @Override
    public List<Role> save(RoleCreateListAction createListAction) {
        List<Role> roles = new ArrayList<>();
        for(Role role : createListAction.roles()) {
            Role savedRole = command.save(RoleCreateAction.buildFrom(role));
            List<Permission> permissionsFromRole = role.permissions();
            List<Permission> savedPermissions = permissionCommand
                    .saveAll(PermissionCreateListAction.buildFrom(permissionsFromRole));
            List<RolePermission> rolePermissions = rolePermissionCommand
                    .saveAll(RolePermissionCreateListAction.buildFrom(savedRole.roleId(), savedPermissions));
            List<String> authorities = rolePermissions.stream()
                    .map(rolePermission -> rolePermission.permission().permissionAuthorityAsString())
                    .toList();
            roles.add(savedRole
                    .withPermissions(savedPermissions)
                    .withAuthorities(authorities));
        }
        return roles;
    }
}
