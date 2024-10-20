package com.project.dvc_barber_service.dto.account;

import com.project.dvc_barber_service.repository.account.AccountEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountMapper extends IObjectMapper<Account, AccountEntity> {

}
