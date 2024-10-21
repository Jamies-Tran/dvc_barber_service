package com.project.dvc_barber_service.dto.auth.role.permission;

import com.project.dvc_barber_service.repository.auth.role.permission.RolePermissionEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRolePermissionMapper extends IObjectMapper<RolePermission, RolePermissionEntity> {
}
