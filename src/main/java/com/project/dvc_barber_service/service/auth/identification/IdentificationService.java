package com.project.dvc_barber_service.service.auth.identification;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dvc_barber_service.config.handler.exception.IdentificationException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.auth.RefreshToken;
import com.project.dvc_barber_service.dto.auth.identification.IIdentificationMapper;
import com.project.dvc_barber_service.dto.auth.identification.Identification;
import com.project.dvc_barber_service.dto.auth.identification.Token;
import com.project.dvc_barber_service.dto.auth.identification.action.RefreshTokenAction;
import com.project.dvc_barber_service.dto.auth.identification.action.VerifyIdentificationAction;
import com.project.dvc_barber_service.dto.cache.MyCache;
import com.project.dvc_barber_service.repository.cache.MyCacheEntity;
import com.project.dvc_barber_service.service.account.AccountQueryService;
import com.project.dvc_barber_service.service.cache.MyCacheCommandService;
import com.project.dvc_barber_service.service.cache.MyCacheQueryService;
import com.project.dvc_barber_service.util.jwt.JwtUtil;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentificationService {
    @NonNull IIdentificationMapper mapper;

    @NonNull AccountQueryService accountQueryService;

    @NonNull MyCacheCommandService myCacheCommandService;

    @NonNull MyCacheQueryService myCacheQueryService;

    @NonNull PasswordEncoder passwordEncoder;

    @NonNull JwtUtil jwtUtil;

    @Value("${app.refresh-token.expire-time}")
    @NonFinal
    Integer refreshTokenExpiredAt;

    /**
     * UC1 - Xác thực và phân quyền
     **/

    /* 1-xác thực người dùng */
    public Identification verify(VerifyIdentificationAction action) {
        Optional<Account> tryToGetAccount = tryToGetAccountByPhone(action.phone());
        if(tryToGetAccount.isEmpty()
                || !verifyPassword(tryToGetAccount.get().password(), action.password())) {
            throw new IdentificationException("Xác thực tài khoản thất bại");
        } else {
            Account account = tryToGetAccount.get();
            String accessToken = jwtUtil.generateAccessToken(account.phone());
            updateRefreshToken(action.phone());
            String refreshToken = cacheRefreshToken(account.phone());
            Token token = Token.buildFrom(accessToken, refreshToken);

            return mapper.from(account).withToken(token);
        }

    }

    private String cacheRefreshToken(String phone) { // lưu cache token
        String token = UUID.randomUUID().toString();
        RefreshToken refreshToken = RefreshToken.buildFrom(token, phone);
        MyCache<RefreshToken> cacheRefreshToken = MyCache.buildFrom(refreshToken, RefreshToken.class.getCanonicalName());
        myCacheCommandService.save(cacheRefreshToken, refreshTokenExpiredAt);

        return token;
    }

    private void updateRefreshToken(String phone) {
        String token = UUID.randomUUID().toString();
        RefreshToken refreshToken = RefreshToken.buildFrom(token, phone);

        myCacheCommandService.updateIdentification(phone, refreshToken);
    }

    private Optional<Account> tryToGetAccountByPhone(String phone) {
        return accountQueryService
                .findByPhone(AccountFindByPhoneAction.buildFrom(phone));
    }

    private Boolean verifyPassword(String accountPassword, String requestPassword) {
        return passwordEncoder.matches(requestPassword, accountPassword);
    }
    /* 1-end */

    /* 2-refresh token */
    public Identification refreshToken(RefreshTokenAction action) {
        try {
            MyCacheEntity refreshTokenCached = myCacheQueryService.findRefreshTokenByToken(action.refreshToken())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy token"));
            RefreshToken refreshToken = convertDataToRefreshToken(refreshTokenCached.getData());
            String accessToken = jwtUtil.generateAccessToken(refreshToken.phone());

            return Identification.builder()
                    .token(Token.buildFrom(accessToken, refreshToken.refreshToken()))
                    .build();
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch(Exception e) {
            log.error("[{}-refreshToken] có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }

    private RefreshToken convertDataToRefreshToken(byte[] data) {
        JsonNode refreshTokenJSNode = AppObjectMapper.getJsonNode(data);
        return RefreshToken.buildFrom(refreshTokenJSNode.get("refreshToken").asText(),
                refreshTokenJSNode.get("phone").asText());
    }
    /* 2-end */
    /**
     * UC1 - end
     **/
}
