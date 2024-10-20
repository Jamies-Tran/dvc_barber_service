package com.project.dvc_barber_service.service.cache;

import com.project.dvc_barber_service.dto.auth.RefreshToken;
import com.project.dvc_barber_service.dto.cache.IMyCacheMapper;
import com.project.dvc_barber_service.dto.cache.MyCache;
import com.project.dvc_barber_service.repository.cache.IMyCacheRepository;
import com.project.dvc_barber_service.repository.cache.MyCacheEntity;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyCacheCommandService {
    @NonNull IMyCacheRepository repository;

    @NonNull IMyCacheMapper mapper;

    @NonNull MyCacheQueryService queryService;

    @NonFinal
    @Value("${app.cache.expire-time}")
    Integer timeToLive;

    /* 1-lưu data với timToLive mặc định (15 phút) */
    public <T> void save(MyCache<T> myCache) {
        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(timeToLive);
        MyCacheEntity newCache = mapper.toEntity(myCache)
                .withExpiredAt(expiredAt);

        repository.save(newCache);
        log.info("[MyCache-save] cached data: {}", myCache);
    }
    /* 1-end */

    /* 2-lưu data với parameter timeToLive */
    public <T> void save(MyCache<T> myCache, Integer timeToLive) {
        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(timeToLive);
        MyCacheEntity newCache = mapper.toEntity(myCache)
                .withExpiredAt(expiredAt);
        repository.save(newCache);

        log.info("[{}] cached data: {}", this.getClass().getSimpleName(), myCache);
    }
    /* 2-end */

    /* 3-cập nhật refresh token cho tài khoản */
    public void updateIdentification(String phone, RefreshToken refreshToken) {
        try {
            byte[] data = AppObjectMapper.convertDataToByte(refreshToken);
            Optional<MyCacheEntity> refreshTokenCached = queryService.findRefreshTokenByPhone(phone);
            refreshTokenCached.ifPresent(myCache -> {
                repository.save(myCache.withData(data));
                repository.deleteById(myCache.getCacheId());
            });
        } catch (Exception e) {
            log.error("[{}] có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }

    }
    /* 3-end */
}
