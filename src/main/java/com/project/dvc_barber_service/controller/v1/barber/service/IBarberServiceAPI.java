package com.project.dvc_barber_service.controller.v1.barber.service;

import com.project.dvc_barber_service.controller.v1.barber.service.models.BarberServiceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Barber Service", description = "Quản lý dịch vụ")
@RequestMapping("/v1/barber-service")
public interface IBarberServiceAPI {
    /**/
    @PostMapping
    @PreAuthorize("hasAuthority('barber-service:create')")
    @Operation(
            summary = "Tạo dịch vụ",
            description = """
                    - Chủ shop thêm mới dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo DM dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Tên dịch vụ đã tồn tại"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Không có quyền truy cập"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token hết hạn"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> save(@RequestBody BarberServiceRequest request);
    /*
    * Use case
    * end
    * */

    /**/
    @GetMapping
    @PreAuthorize("hasAuthority('barber-service:view-list')")
    @Operation(
            summary = "Tìm kiếm danh sách dịch vụ",
            description = """
                    - Chủ shop xem danh sách dịch vụ trong hệ thống
                    - QL chi nhánh xem danh sách dịch vụ trong hệ thông
                    - Nhân viên phục vụ xem danh sách dịch vụ trong hệ thống
                    - Tiếp tân xem danh sách dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy danh sách dịch vụ"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Không có quyền truy cập"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token hết hạn"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "durationTypeCode", defaultValue = "") String durationTypeCode,
            @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
            @RequestParam(required = false, value = "categoryId", defaultValue = "") Long categoryId,
            @RequestParam(required = false, value = "priceRange", defaultValue = "") List<Long> priceRange,
            @RequestParam(required = false, value = "estimateDurationRange", defaultValue = "") List<Integer> estimateDurationRange,
            @RequestParam(required = false, value = "sorter", defaultValue = "") String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize
    );
    /*
     * Use case
     * end
     * */
}
