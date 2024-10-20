package com.project.dvc_barber_service.dto.auth.role;

import com.project.dvc_barber_service.repository.auth.role.RoleEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleMapper extends IObjectMapper<Role, RoleEntity> {
}
