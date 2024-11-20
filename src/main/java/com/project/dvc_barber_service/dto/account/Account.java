package com.project.dvc_barber_service.dto.account;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.media.Media;
import com.project.dvc_barber_service.enums.gender.EGender;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import lombok.Builder;
import lombok.With;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@With
@Builder
public record Account(
        Long accountId,
        Long roleId,
        String accountCode,
        String firstName,
        String lastName,
        String genderCode,
        String genderName,
        String phone,
        String address,
        String password,
        LocalDateTime dob,
        String avatar,
        List<Media> openingImageMedia,
        String expertiseCode,
        String expertiseName,
        String statusCode,
        String statusName,
        Role role
) {

    public Account {
        if(!StringUtils.hasText(genderName)) {
            Optional<EGender> gender = EGender.getByCode(genderCode);
            if(gender.isPresent()) {
                genderName = gender.get().getName();
            }
        }
    }

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
