package com.project.dvc_barber_service.config.security;

import com.project.dvc_barber_service.service.auth.filter.AppFilter;
import com.project.dvc_barber_service.service.auth.handler.AppAuthenticationEntryPoint;
import com.project.dvc_barber_service.service.auth.user.AppUserDetailService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    @NonNull AppFilter appFilter;

    @NonNull AppUserDetailService userDetailService;

    @NonNull PasswordEncoder passwordEncoder;

    @NonNull AppAuthenticationEntryPoint appAuthenticationEntryPoint;

    private CorsConfigurationSource customCorsConfiguration() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        cors.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors(httpCors -> httpCors.configurationSource(customCorsConfiguration()))
                .csrf(httpCsrf -> httpCsrf.disable())
                .sessionManagement(httpSession -> httpSession.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(httpAuthorization -> httpAuthorization.requestMatchers(
                        AntPathRequestMatcher.antMatcher("/swagger-ui/index.html"),
                        AntPathRequestMatcher.antMatcher("/swagger-ui/*"),
                        AntPathRequestMatcher.antMatcher("/v3/api-docs/*"),
                        AntPathRequestMatcher.antMatcher("/v3/api-docs"),
                        AntPathRequestMatcher.antMatcher("/auth/**"),
                        AntPathRequestMatcher.antMatcher("/auth/*"),
                        AntPathRequestMatcher.antMatcher("/common/**")).permitAll())
                .authorizeHttpRequests(httpAuthorization -> httpAuthorization.anyRequest().authenticated())
                .exceptionHandling(excHandler -> excHandler.authenticationEntryPoint(appAuthenticationEntryPoint))
                .build();
    }

}
