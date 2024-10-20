package com.project.dvc_barber_service.dto.auth.permission;

import lombok.Builder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Builder
public record Permission(
        Long permissionId,
        String permissionFor,
        String permissionCode,
        String permissionName
) {
    public static Permission empty() {
        return Permission.builder().build();
    }

    public String permissionAuthorityAsString() {
        return "%s:%s".formatted(permissionFor, permissionCode);
    }

    public SimpleGrantedAuthority permissionAuthority() {
        return new SimpleGrantedAuthority(permissionAuthorityAsString());
    }
}
