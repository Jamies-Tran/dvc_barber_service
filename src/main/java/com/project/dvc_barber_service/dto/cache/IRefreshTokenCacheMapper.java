package com.project.dvc_barber_service.dto.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dvc_barber_service.dto.auth.RefreshToken;
import com.project.dvc_barber_service.repository.cache.MyCacheEntity;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import org.mapstruct.Mapping;

public interface IRefreshTokenCacheMapper {
    @Mapping(target = "data", expression = "java(convertBlobToRefreshToken(entity))")
    MyCache<RefreshToken> convertToRefreshToken(MyCacheEntity entity);

    default RefreshToken convertBlobToRefreshToken(MyCacheEntity entity) {
        try {
            JsonNode refreshTokenJsNode = AppObjectMapper.objectMapper().readTree(entity.getData());
            return RefreshToken.buildFrom(refreshTokenJsNode.get("refreshToken").asText(),
                    refreshTokenJsNode.get("phone").asText());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
