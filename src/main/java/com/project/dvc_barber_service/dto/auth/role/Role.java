package com.project.dvc_barber_service.dto.auth.role;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.enums.role.ERole;
import lombok.Builder;
import lombok.With;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@With
@Builder
public record Role(
        Long roleId,
        String roleName,
        String roleCode,
        List<String> authorities,
        List<Permission> permissions
) {
    public static Role buildFrom(ERole role, List<Permission> permissions) {
        return Role.builder()
                .roleCode(role.getCode())
                .roleName(role.getName())
                .permissions(permissions)
                .build();
    }

    public static Role buildFrom(ERole role) {
        return Role.builder()
                .roleCode(role.getCode())
                .roleName(role.getName())
                .build();
    }

    public String roleAuthorityAsString() {
        return "ROLE_%s".formatted(roleCode);
    }

    public SimpleGrantedAuthority roleAuthority() {
        return new SimpleGrantedAuthority(roleAuthorityAsString());
    }
}
