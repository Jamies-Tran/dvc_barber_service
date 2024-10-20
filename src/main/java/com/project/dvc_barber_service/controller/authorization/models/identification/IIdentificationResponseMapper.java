package com.project.dvc_barber_service.controller.authorization.models.identification;

import com.project.dvc_barber_service.dto.auth.identification.Identification;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IIdentificationResponseMapper extends IModelMapper<IdentificationResponse, Identification> {
}
