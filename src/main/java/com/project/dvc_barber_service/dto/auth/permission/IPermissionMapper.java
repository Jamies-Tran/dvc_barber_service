package com.project.dvc_barber_service.dto.auth.permission;

import com.project.dvc_barber_service.repository.auth.permission.PermissionEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPermissionMapper extends IObjectMapper<Permission, PermissionEntity> {
}
