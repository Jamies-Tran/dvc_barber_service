package com.project.dvc_barber_service.dto.auth.permission;

import com.project.dvc_barber_service.enums.permission.EPermission;
import lombok.Builder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Builder
public record Permission(
        Long permissionId,
        String permissionKey,
        String permissionCode,
        String permissionName
) {
    public static Permission buildFrom(EPermission permission) {
        return Permission.builder()
                .permissionCode(permission.getCode())
                .permissionName(permission.getName())
                .permissionKey(permission.getKey())
                .build();
    }

    public static Permission empty() {
        return Permission.builder().build();
    }

    public String permissionAuthorityAsString() {
        return "%s:%s".formatted(permissionKey, permissionName);
    }

    public SimpleGrantedAuthority permissionAuthority() {
        return new SimpleGrantedAuthority(permissionAuthorityAsString());
    }
}
