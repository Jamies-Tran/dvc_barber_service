package com.project.dvc_barber_service.controller.authorization.models;

import com.project.dvc_barber_service.dto.auth.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleModelMapper {
    Role toDto(RoleRequest model);

    RoleResponse toModel(Role dto);
}
