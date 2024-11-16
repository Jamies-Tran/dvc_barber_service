package com.project.dvc_barber_service.enums.status.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAccountStatus {
    ENABLED("ENABLED", "Hoạt động"),
    DISABLED("DISABLED", "Vô hiệu hóa");

    String code;
    String name;

    public static List<EAccountStatus> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search)
                        || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}
