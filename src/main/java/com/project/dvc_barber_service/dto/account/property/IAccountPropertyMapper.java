package com.project.dvc_barber_service.dto.account.property;

import com.project.dvc_barber_service.repository.account.properity.AccountPropertyEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountPropertyMapper extends IObjectMapper<AccountProperty, AccountPropertyEntity> {
}
