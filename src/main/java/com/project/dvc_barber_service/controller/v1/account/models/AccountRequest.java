package com.project.dvc_barber_service.controller.v1.account.models;

import com.project.dvc_barber_service.util.request.MediaRequest;

import java.time.LocalDateTime;
import java.util.List;

public record AccountRequest(
        Long branchId,
        String firstName,
        String lastName,
        LocalDateTime dob,
        String address,
        String phone,
        String avatar,
        List<MediaRequest> openingImageMedia,
        String expertiseCode,
        String roleCode
) {
}
