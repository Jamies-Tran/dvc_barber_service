package com.project.dvc_barber_service.controller.v1.branch;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Branch", description = "Quản lý chi nhánh")
@RequestMapping("/v1/branch/{branchId}")
public interface IBranchPathAPI {
    @GetMapping
    @PreAuthorize("hasAuthority('branch:view-detail')")
    ResponseEntity<?> findById(@PathVariable Long branchId);
}
