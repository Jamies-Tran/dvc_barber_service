package com.project.dvc_barber_service.controller.v1.account.models;

import com.project.dvc_barber_service.util.request.MediaRequest;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountUpdateRequest(
        String firstName,
        String lastName,
        String genderCode,
        LocalDateTime dob,
        String address,
        String phone,
        String avatar,
        List<MediaRequest> openingImageMedia
) {
}
