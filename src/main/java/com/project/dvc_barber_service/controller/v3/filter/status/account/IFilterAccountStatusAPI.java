package com.project.dvc_barber_service.controller.v3.filter.status.account;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v3/filter-account-status")
@Tag(name = "Filter", description = "Filter dữ liệu trong hệ thống")
public interface IFilterAccountStatusAPI {
    @GetMapping
    ResponseEntity<?> filterAccountStatus(
            @RequestParam(required = false, value = "search", defaultValue = "") String search);
}
