package com.project.dvc_barber_service.dto.expertise;

import com.project.dvc_barber_service.repository.database.expertise.ExpertiseEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IExpertiseMapper extends IObjectMapper<Expertise, ExpertiseEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ExpertiseEntity entity, Expertise dto);
}
