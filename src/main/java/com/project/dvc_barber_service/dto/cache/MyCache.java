package com.project.dvc_barber_service.dto.cache;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@With
@Builder
public record MyCache<T>(
        Long cacheId,
        T data,
        String cacheKey,
        LocalDateTime expiredAt
) {
    public static <T> MyCache<T> buildFrom(T data, String cacheKey) {
        return MyCache.<T>builder()
                .data(data)
                .cacheKey(cacheKey)
                .build();
    }
}
