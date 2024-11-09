package com.project.dvc_barber_service.dto.account;

import com.project.dvc_barber_service.dto.media.Media;
import com.project.dvc_barber_service.repository.account.AccountEntity;
import com.project.dvc_barber_service.repository.account.dao.AccountLoginDAO;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountMapper extends IObjectMapper<Account, AccountEntity> {
    AccountLogin toDto(AccountLoginDAO dao);

    Account toDto(AccountEntity entity, List<Media> openingImageMedia);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "openingImage", source = "openingImage")
    void update(@MappingTarget AccountEntity entity, Account dto, byte[] openingImage);
}
