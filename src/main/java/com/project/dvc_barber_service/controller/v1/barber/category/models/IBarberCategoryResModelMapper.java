package com.project.dvc_barber_service.controller.v1.barber.category.models;

import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBarberCategoryResModelMapper extends IModelMapper<BarberCategoryResponse, BarberCategory> {
}
