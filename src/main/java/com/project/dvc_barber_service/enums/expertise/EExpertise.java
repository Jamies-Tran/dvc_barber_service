package com.project.dvc_barber_service.enums.expertise;

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
public enum EExpertise {
    STYLIST("STYLIST", "Thợ tạo mẫu tóc"),
    MASSAGE_THERAPIST("MASSAGE_THERAPIST", "Chuyên viên massage"),;

    String code;
    String name;

    public static Optional<EExpertise> getByCode(String code) {
        return Stream.of(values())
                .filter(x -> Objects.equals(x.getCode(), code))
                .findAny();
    }

    public static List<EExpertise> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search) || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}
