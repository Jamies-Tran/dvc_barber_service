package com.project.dvc_barber_service.controller.v1.barber.category;

import com.project.dvc_barber_service.controller.v1.barber.category.models.BarberCategoryRequest;
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

@RequestMapping("/v1/barber-category")
@Tag(name = "Barber Category", description = "Quản lý danh mục dịch vụ")
public interface IBarberCategoryAPI {
    /*
     * Use case
     * Chủ shop thêm mới DM dịch vụ trong hệ thống
     * start
     * */
    @PostMapping
    @PreAuthorize("hasAuthority('barber-category:create')")
    @Operation(
            summary = "Tạo DM dịch vụ",
            description = """
                    - Chủ shop thêm mới DM dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo DM dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> save(@RequestBody BarberCategoryRequest request);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem danh sách DM dịch vụ trong hệ thống
     * QL chi nhánh xem danh sách DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem danh sách DM dịch vụ trong hệ thống
     * Tiếp tân xem danh sách DM dịch vụ trong hệ thống
     * Khách hàng xem danh sách DM dịch vụ trong hệ thống
     * start
     * */

    @GetMapping
    @PreAuthorize("hasAuthority('barber-category:view-list')")
    @Operation(
            summary = "Tìm kiếm danh sách DM dịch vụ",
            description = """
                    - Chủ shop xem danh sách DM dịch vụ trong hệ thống
                    - QL chi nhánh xem danh sách DM dịch vụ trong hệ thống
                    - Nhân viên phục vụ xem danh sách DM dịch vụ trong hệ thống
                    - Tiếp tân xem danh sách DM dịch vụ trong hệ thống
                    - Khách hàng xem danh sách DM dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm kiếm danh sách DM dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> findAll(
             @RequestParam(required = false, value = "search", defaultValue = "") String search,
             @RequestParam(required = false, value = "categoryCodes", defaultValue = "") List<String> categoryCodes,
             @RequestParam(required = false, value = "sorter", defaultValue = "") String sorter,
             @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
             @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize
    );
    /*
     * Use case
     * end
     * */
}
