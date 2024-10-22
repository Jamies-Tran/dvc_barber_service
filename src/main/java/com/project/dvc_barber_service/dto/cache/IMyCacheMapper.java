package com.project.dvc_barber_service.dto.cache;

import com.project.dvc_barber_service.repository.cache.MyCacheEntity;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMyCacheMapper extends IRefreshTokenCacheMapper {
    @Mapping(target = "data", expression = "java(convertDataToBlob(dto))")
    MyCacheEntity toEntity(MyCache dto);

    default byte[] convertDataToBlob(MyCache dto) {
        try {
            return AppObjectMapper.objectMapper().writeValueAsBytes(dto.data());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
