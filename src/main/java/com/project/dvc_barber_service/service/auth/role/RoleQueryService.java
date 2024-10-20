package com.project.dvc_barber_service.service.auth.role;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.permission.action.PermissionFindByRoleIdAction;
import com.project.dvc_barber_service.dto.auth.role.IRoleMapper;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleFindByIdAction;
import com.project.dvc_barber_service.repository.auth.role.IRoleRepository;
import com.project.dvc_barber_service.repository.auth.role.RoleEntity;
import com.project.dvc_barber_service.service.auth.permission.PermissionQueryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleQueryService {
    @NonNull IRoleRepository repository;

    @NonNull IRoleMapper mapper;

    @NonNull PermissionQueryService permissionQueryService;

    public Optional<Role> findById(RoleFindByIdAction action) {
        Long roleId = action.roleId();
        Optional<RoleEntity> foundRole = repository.findById(roleId);
        List<Permission> permissions = permissionQueryService.findByRoleId(PermissionFindByRoleIdAction.buildFrom(roleId));

        return foundRole.map(role -> mapper.toDto(role).withPermissions(permissions));
    }
}
