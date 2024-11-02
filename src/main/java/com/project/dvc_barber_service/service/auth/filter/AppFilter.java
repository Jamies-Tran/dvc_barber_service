package com.project.dvc_barber_service.service.auth.filter;

import com.project.dvc_barber_service.config.handler.exception.IdentificationException;
import com.project.dvc_barber_service.service.auth.user.AppUserDetailService;
import com.project.dvc_barber_service.util.jwt.JwtUtil;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import com.project.dvc_barber_service.util.response.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppFilter extends OncePerRequestFilter {
    @NonNull AppUserDetailService appUserDetailService;

    @NonNull JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            jwtUtil.validateToken(request);
            String userPhone = jwtUtil.getIdentityFromRequest(request);
            if(StringUtils.hasText(userPhone)) {
                UserDetails userDetails = appUserDetailService.loadUserByUsername(userPhone);
                UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                        .authenticated(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
                log.info("Đã đăng nhập bằng sdt: {}", userPhone);
            }

            filterChain.doFilter(request, response);
        } catch (IdentificationException e) {
            log.error("[{}-doFilterInternal] Token không hợp lệ", this.getClass().getSimpleName());
            ErrorResponse errorResponse = ErrorResponse
                    .of(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "token đã hết hạn");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } catch (RuntimeException e) {
            log.error("[{}-doFilterInternal] Xác thực that bại", this.getClass().getSimpleName());
            ErrorResponse errorResponse = ErrorResponse
                    .of(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Có lỗi xảy ra");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        }
    }
}
