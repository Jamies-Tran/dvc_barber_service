package com.project.dvc_barber_service.controller.v3.filter.gender;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v3/filter-gender")
@Tag(name = "Filter", description = "Filter dữ liệu trong hệ thống")
public interface IFilterGenderAPI {
    @GetMapping
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search);
}
