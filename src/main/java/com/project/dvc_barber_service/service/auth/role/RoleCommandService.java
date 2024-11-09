package com.project.dvc_barber_service.service.auth.role;

import com.project.dvc_barber_service.dto.auth.role.IRoleMapper;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateListAction;
import com.project.dvc_barber_service.repository.database.auth.role.IRoleRepository;
import com.project.dvc_barber_service.repository.database.auth.role.RoleEntity;
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
public class RoleCommandService {
    @NonNull IRoleRepository repository;

    @NonNull IRoleMapper mapper;

    public List<Role> saveAll(RoleCreateListAction action) {
        List<Role> roles = action.roles();
        List<Role> newRoles = new ArrayList<>();
        for(Role role : roles) {
            repository.findByRoleCode(role.roleCode())
                    .ifPresentOrElse(roleEntity -> {
                        log.info("[{}] Role đã tồn tại", roleEntity.getRoleCode());
                        newRoles.add(mapper.toDto(roleEntity));
                    }, () -> {
                        RoleEntity newRole = repository.save(mapper.toEntity(role));
                        newRoles.add(mapper.toDto(newRole));
                    });
        }
        return newRoles;
    }
}
