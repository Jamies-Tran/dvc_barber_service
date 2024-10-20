package com.project.dvc_barber_service.service.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dvc_barber_service.dto.auth.RefreshToken;
import com.project.dvc_barber_service.repository.cache.IMyCacheRepository;
import com.project.dvc_barber_service.repository.cache.MyCacheEntity;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyCacheQueryService {

    @NonNull IMyCacheRepository repository;

    /* 1-tìm refresh token bằng số điện thoại */
    public Optional<MyCacheEntity> findRefreshTokenByPhone(String phone) {
        try {
            return repository.findRefreshToken(RefreshToken.class.getCanonicalName())
                    .stream()
                    .filter(cache -> {
                        RefreshToken refreshToken = convertFromJson(cache);
                        return Objects.equals(phone, refreshToken.phone());
                    }).findAny();
        } catch (Exception e) {
            log.error("[{}-findRefreshToken] có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }
    /* 1-end */

    /* 2-tìm rerfresh token bằng token */
    public Optional<MyCacheEntity> findRefreshTokenByToken(String token) {
        try {
            LocalDateTime now = LocalDateTime.now();
            return repository.findRefreshToken(RefreshToken.class.getCanonicalName())
                    .stream()
                    .filter(cached -> {
                        RefreshToken refreshToken = convertFromJson(cached);
                        return Objects.equals(refreshToken.refreshToken(), token)
                                && (cached.getExpiredAt().isAfter(now) || cached.getExpiredAt().equals(now));
                    }).findAny();
        } catch (Exception e) {
            log.error("[{}-findRefreshToken]", this.getClass().getSimpleName());
            throw new RuntimeException();
        }
    }
    /* 2-end */

    private RefreshToken convertFromJson(MyCacheEntity cache) {
        try {
            JsonNode json = AppObjectMapper.getJsonNode(cache.getData());
            return RefreshToken.buildFrom(json.get("refreshToken").asText(), json.get("phone").asText());
        } catch (Exception e) {
            log.error("[{}-convertFromJson] có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }
}
