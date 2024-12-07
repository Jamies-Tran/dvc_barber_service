package com.project.dvc_barber_service.controller.v1.branch;

import com.project.dvc_barber_service.controller.v1.branch.models.BranchRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Branch", description = "Quản lý chi nhánh")
@RequestMapping("/v1/branch")
public interface IBranchAPI {
    @PostMapping
    @PreAuthorize("hasAuthority('branch:create')")
    ResponseEntity<?> save(@RequestBody BranchRequest request);
}
