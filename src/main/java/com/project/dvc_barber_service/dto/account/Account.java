package com.project.dvc_barber_service.dto.account;

import com.project.dvc_barber_service.dto.account.property.AccountProperty;
import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.Role;
import lombok.Builder;
import lombok.With;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@With
@Builder
public record Account(
        Long accountId,
        String firstName,
        String lastName,
        String phone,
        String address,
        String password,
        AccountProperty accountProperty,
        Role role
) {
    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority roleAuth = role.roleAuthority();
        List<SimpleGrantedAuthority> permissionAuths = role.permissions()
                .stream()
                .map(Permission::permissionAuthority)
                .toList();
        authorities.add(roleAuth);
        authorities.addAll(permissionAuths);
        return authorities;
    }
}
