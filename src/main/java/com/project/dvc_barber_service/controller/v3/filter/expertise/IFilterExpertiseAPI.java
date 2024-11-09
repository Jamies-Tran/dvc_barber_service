package com.project.dvc_barber_service.controller.v3.filter.expertise;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v3/filter-expertise")
@Tag(name = "Filter", description = "Filter dữ liệu trong hệ thống")
public interface IFilterExpertiseAPI {
    @GetMapping
    ResponseEntity<?> findAllByName(
            @RequestParam(required = false, name = "search", defaultValue = "") String search);
}
