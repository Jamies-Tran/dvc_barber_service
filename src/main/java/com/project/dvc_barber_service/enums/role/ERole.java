package com.project.dvc_barber_service.enums.role;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ERole {
    SHOP_OWNER("SHOP_OWNER", "Chủ sở hữu", "SHO"),
    BRANCH_MANAGER("BRANCH_MANAGER", "Quản lý chi nhánh", "BRM"),
    SERVICE_STAFF("SERVICE_STAFF", "Nhân viên phục vụ", "SER"),
    RECEPTIONIST("RECEPTIONIST", "Tiếp tân", "REC"),
    CUSTOMER("CUSTOMER", "Khách hàng", "CUS");

    String code;
    String name;
    String preCode;

    public static Optional<ERole> getByCode(String code) {
        return Stream.of(values())
                .filter(x -> Objects.equals(x.getCode(), code))
                .findAny();
    }

    public static List<ERole> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search)
                        || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    public static List<ERole> getList() {
        return Stream.of(values()).toList();
    }

    public String getDefaultRoleCode() {
        return "%s_%s".formatted(this.preCode, LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("ddMMyyhhmmss")));
    }
}
