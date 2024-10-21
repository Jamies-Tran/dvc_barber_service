package com.project.dvc_barber_service.dto.auth.identification;

import com.project.dvc_barber_service.dto.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IIdentificationMapper {
    @Mapping(target = "accountCode", source = "accountProperty.accountCode")
    Identification from(Account account);
}
