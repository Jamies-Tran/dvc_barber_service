package com.project.dvc_barber_service.controller.v1.barber.category;

import com.project.dvc_barber_service.controller.v1.barber.category.models.BarberCategoryUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/barber-category/{barberCategoryId}")
@Tag(name = "Barber Category", description = "Quản lý danh mục dịch vụ")
public interface IBarberCategoryPathAPI {
    /*
     * Use case
     * Chủ shop xem chi tiết DM dịch vụ trong hệ thống
     * QL chi nhánh xem chi tiết DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem chi tiết DM dịch vụ trong hệ thống
     * Tiếp tân xem chi tiết DM dịch vụ trong hệ thống
     * Khách hàng xem chi tiết DM dịch vụ trong hệ thống
     * start
     * */
    @GetMapping
    @PreAuthorize("hasAuthority('barber-category:view')")
    @Operation(
            summary = "Tìm kiếm chi tiết DM dịch vụ",
            description = """
                    - Chủ shop xem chi tiết DM dịch vụ trong hệ thống
                    - QL chi nhánh xem chi tiết DM dịch vụ trong hệ thống
                    - Nhân viên phục vụ xem chi tiết DM dịch vụ trong hệ thống
                    - Tiếp tân xem chi tiết DM dịch vụ trong hệ thống
                    - Khách hàng xem chi tiết DM dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm kiếm chi tiết DM dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy DM dịch vụ"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> findById(@PathVariable Long barberCategoryId);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop cập nhật DM dịch vụ trong hệ thống
     * start
     * */
    @PutMapping
    @PreAuthorize("hasAuthority('barber-category:update')")
    @Operation(
            summary = "Cập nhật DM dịch vụ",
            description = """
                    - Chủ shop cập nhật DM dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật DM dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy DM dịch vụ"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> update(@PathVariable Long barberCategoryId, @RequestBody BarberCategoryUpdateRequest request);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa DM dịch vụ trong hệ thông
     * start
     * */
    @DeleteMapping
    @PreAuthorize("hasAuthority('barber-category:delete')")
    @Operation(
            summary = "Xóa DM dịch vụ",
            description = """
                    - Chủ shop xóa DM dịch vụ trong hệ thông
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Xóa DM dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy DM dịch vụ"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> delete(@PathVariable Long barberCategoryId);
    /*
     * Use case
     * end
     * */
}
