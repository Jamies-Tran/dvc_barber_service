package com.project.dvc_barber_service.controller.v1.branch.models;

import java.time.LocalDateTime;

public record BranchRequest(
        String contact,
        String address,
        LocalDateTime openTime,
        LocalDateTime closeTime,
        Integer capacity
) {
}
