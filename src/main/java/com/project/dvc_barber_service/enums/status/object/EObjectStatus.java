package com.project.dvc_barber_service.enums.status.object;

import com.project.dvc_barber_service.enums.status.account.EAccountStatus;
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
public enum EObjectStatus {
    ACTIVATED("ACTIVATED", "Hoạt động");

    String code;
    String name;

    public static List<EObjectStatus> getList(String search) {
        return Stream.of(values())
                .filter(x -> !StringUtils.hasText(search)
                        || x.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}
