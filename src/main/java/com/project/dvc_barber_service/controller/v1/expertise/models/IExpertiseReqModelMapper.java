package com.project.dvc_barber_service.controller.v1.expertise.models;

import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IExpertiseReqModelMapper extends IModelMapper<ExpertiseRequest, Expertise> {
    Expertise toDto(ExpertiseUpdateRequest model);
}
