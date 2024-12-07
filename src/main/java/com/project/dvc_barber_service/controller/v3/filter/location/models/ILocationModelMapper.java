package com.project.dvc_barber_service.controller.v3.filter.location.models;

import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILocationModelMapper extends IModelMapper<LocationResponse, com.project.dvc_barber_service.repository.feign.place.location.models.LocationResponse> {
}
