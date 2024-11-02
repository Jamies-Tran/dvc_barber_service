package com.project.dvc_barber_service.config.context;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.service.account.AccountQueryService;
import com.project.dvc_barber_service.util.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestContext {
    @NonNull AccountQueryService accountQueryService;

    @NonNull JwtUtil jwtUtil;

    private HttpServletRequest request() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
    }

    public AccountLogin getAccount() {
        try {
            String phone = jwtUtil.getIdentityFromRequest(request());
            return accountQueryService
                    .findAccountLoginByPhone(AccountFindByPhoneAction.buildFrom(phone))
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));
        } catch (ResourceNotFoundException e) {
            log.error("[{}-getAccount] Không tìm thấy tài khoản", this.getClass().getSimpleName());
            throw e;
        } catch (Exception e) {
            log.error("[{}-getAccount] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }

    public Optional<AccountLogin> tryToGetAccount() {
        try {
            String phone = jwtUtil.getIdentityFromRequest(request());
            return accountQueryService
                    .findAccountLoginByPhone(AccountFindByPhoneAction.buildFrom(phone));
        } catch (Exception e) {
            log.error("[{}-tryToGetAccount] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}
