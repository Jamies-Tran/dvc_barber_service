package com.project.dvc_barber_service.controller.v1.barber.service.models;

import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBarberServiceReqModelMapper extends IModelMapper<BarberServiceRequest, BarberService> {
    BarberService toDto(BarberServiceUpdateRequest model);
}
