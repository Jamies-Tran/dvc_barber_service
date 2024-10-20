package com.project.dvc_barber_service.controller.authorization.models.identification;

import com.project.dvc_barber_service.dto.auth.identification.Identification;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRefreshTokenModelMapper {
    RefreshIdentificationResponse from(Identification identification);
}
