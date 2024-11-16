package com.project.dvc_barber_service.controller.v1.expertise.models;

import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IExpertiseResModelMapper extends IModelMapper<ExpertiseResponse, Expertise> {
}
