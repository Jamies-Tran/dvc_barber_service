package com.project.dvc_barber_service.controller.v1.branch.service;

import com.project.dvc_barber_service.controller.v1.branch.service.models.BranchServiceListRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Branch Service", description = "Quản lý dịch vụ trong chi nhánh")
@RequestMapping("/v1/branch-service/{branchId}")
public interface IBranchServiceAPI {
    @PostMapping
    @PreAuthorize("hasAuthority('branch:add-service')")
    ResponseEntity<?> addService(@PathVariable Long branchId,
                                 @RequestBody BranchServiceListRequest request);
}
