package com.project.dvc_barber_service.controller.v1.barber.service;

import com.project.dvc_barber_service.controller.v1.barber.service.models.BarberServiceUpdateRequest;
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

@Tag(name = "Barber Service", description = "Quản lý dịch vụ")
@RequestMapping("/v1/barber-service/{barberServiceId}")
public interface IBarberServicePathAPI {
    /**/
    @GetMapping
    @PreAuthorize("hasAuthority('barber-service:view-detail')")
    @Operation(
            summary = "Tìm kiếm chi tiết dịch vụ",
            description = """
                    - Chủ shop xem chi tiết dịch vụ trong hệ thống
                    - QL chi nhánh xem chi tiết dịch vụ trong hệ thông
                    - Tiếp tân xem chi tiết dịch vụ trong hệ thống
                    - Nhân viên phục vụ xem chi tiết dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy danh sách dịch vụ"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy dịch vụ"),
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
    ResponseEntity<?> findById(@PathVariable Long barberServiceId);
    /*
    * Use case
    * end
    * */

    /**/
    @PutMapping
    @PreAuthorize("hasAuthority('barber-service:update')")
    @Operation(
            summary = "Cập nhật dịch vụ",
            description = """
                    - Chủ shop cập nhật thông tin dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy dịch vụ"),
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
    ResponseEntity<?> update(@PathVariable Long barberServiceId, @RequestBody BarberServiceUpdateRequest request);
    /*
     * Use case
     * end
     * */

    /**/
    @DeleteMapping
    @PreAuthorize("hasAuthority('barber-service:delete')")
    @Operation(
            summary = "Xóa dịch vụ",
            description = """
                    - Chủ shop cập nhật thông tin dịch vụ trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Xóa dịch vụ thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy dịch vụ"),
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
    ResponseEntity<?> delete(@PathVariable Long barberServiceId);
    /*
     * Use case
     * end
     * */
}
