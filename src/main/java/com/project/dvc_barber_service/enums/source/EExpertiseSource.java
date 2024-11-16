package com.project.dvc_barber_service.enums.source;

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
public enum EExpertiseSource {
    FROM_SYSTEM("FROM_SYSTEM", "Từ hệ thống"),
    FROM_BRANCH("FROM_BRANCH", "Từ chi nhánh");

    public static List<EExpertiseSource> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search)
                        || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    String code;
    String name;
}
