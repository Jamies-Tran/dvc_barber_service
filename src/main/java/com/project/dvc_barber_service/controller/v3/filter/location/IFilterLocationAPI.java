package com.project.dvc_barber_service.controller.v3.filter.location;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v3/filter-location")
@Tag(name = "Filter", description = "Filter dữ liệu trong hệ thống")
public interface IFilterLocationAPI {
    @GetMapping
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false, value = "radius", defaultValue = "30") Integer radius,
            @RequestParam(required = false, value = "more_compound", defaultValue = "false") Boolean moreCompound);
}
