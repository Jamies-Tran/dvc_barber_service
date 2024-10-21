package com.project.dvc_barber_service.service.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dvc_barber_service.util.response.ValueResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;

@Configuration
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ValueResponse<?> valueRes = ValueResponse.handler("Không có quyền truy cập", unauthorized);
        response.setContentType("application/json");
        response.setStatus(unauthorized.value());
        OutputStream os = response.getOutputStream();
        objectMapper.writeValue(os, valueRes);

        os.flush();
    }
}
