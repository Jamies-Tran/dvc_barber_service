package com.project.dvc_barber_service.dto.auth.role;

import com.project.dvc_barber_service.dto.auth.permission.Permission;
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
    public String roleAuthorityAsString() {
        return "ROLE_%s".formatted(roleCode);
    }

    public SimpleGrantedAuthority roleAuthority() {
        return new SimpleGrantedAuthority(roleAuthorityAsString());
    }
}
