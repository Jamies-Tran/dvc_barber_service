package com.project.dvc_barber_service.enums.status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EDeleteStatus {
    DELETED("DELETED", "Đã xóa");

    String code;
    String name;
}
