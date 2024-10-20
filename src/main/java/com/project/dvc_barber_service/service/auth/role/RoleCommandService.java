package com.project.dvc_barber_service.service.auth.role;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.IRoleMapper;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateAction;
import com.project.dvc_barber_service.repository.auth.role.IRoleRepository;
import com.project.dvc_barber_service.repository.auth.role.RoleEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleCommandService {
    @NonNull IRoleRepository repository;

    @NonNull IRoleMapper mapper;

    public Role save(RoleCreateAction createListAction) {
        RoleEntity newRole = mapper.toEntity(createListAction.role());
        List<Permission> permissions = createListAction.role().permissions();
        return Stream.of(repository.save(newRole))
                .map(xx -> mapper.toDto(xx).withPermissions(permissions))
                .findAny().get();
    }
}
