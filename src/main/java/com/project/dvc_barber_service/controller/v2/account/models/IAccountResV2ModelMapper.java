package com.project.dvc_barber_service.controller.v2.account.models;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "AccountResModelMapperV2")
public interface IAccountResV2ModelMapper extends IModelMapper<AccountV2Response, Account> {
}
