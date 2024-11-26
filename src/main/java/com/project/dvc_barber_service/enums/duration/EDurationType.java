package com.project.dvc_barber_service.enums.duration;

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
public enum EDurationType {
    HOUR("HOUR", "Giờ", 60),
    MINUTE("MINUTE", "Phút", 1);

    String code;
    String name;
    Integer minuteValue;

    public static List<EDurationType> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search) || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    public static Optional<EDurationType> getByCode(String code) {
        return Stream.of(values())
                .filter(x -> Objects.equals(x.getCode(), code))
                .findAny();
    }
}
