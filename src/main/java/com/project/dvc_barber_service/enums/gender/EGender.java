package com.project.dvc_barber_service.enums.gender;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EGender {
    MALE("MALE", "Nam"),
    FEMALE("FEMALE", "Nữ"),
    OTHER("OTHER", "Khác");

    String code;
    String name;

    public static List<EGender> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search) || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    public static Optional<EGender> getByCode(String code) {
        return Stream.of(values())
                .filter(x -> Objects.equals(x.getCode(), code))
                .findAny();
    }
}
