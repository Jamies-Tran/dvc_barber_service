package com.project.dvc_barber_service.dto.barber.category;

import com.project.dvc_barber_service.repository.database.barber.category.BarberCategoryEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBarberCategoryMapper extends IObjectMapper<BarberCategory, BarberCategoryEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget BarberCategoryEntity entity, BarberCategory dto);
}
