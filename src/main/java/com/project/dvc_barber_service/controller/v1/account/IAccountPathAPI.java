package com.project.dvc_barber_service.controller.v1.account;

import com.project.dvc_barber_service.controller.v1.account.models.AccountUpdateRequest;
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

@RequestMapping("/v1/account/{accountId}")
@Tag(name = "Account", description = "Quản lý tài khoản")
public interface IAccountPathAPI {

    /*
     * Use case
     * Chủ shop cập nhật thông tin tài khoản QL chi nhánh
     * Chủ shop cập nhật thông tin tài khoản nhân viên cắt tóc
     * Chủ shop cập nhật thông tin tài khoản nhân viên massage
     * Chủ shop cập nhật thông tin tài khoản tiếp tân
     *
     * QL chi nhánh cập nhật tài khoản nhân viên cắt tóc
     * QL chi nhánh cập nhật tài khoản nhân viên massage
     * QL chi nhánh cập nhật tài khoản tiếp tân
     *
     * start
     * */
    @PutMapping
    @PreAuthorize("hasAuthority('account:update')")
    @Operation(summary = "Cập nhật thông tin tài khoản")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thông tin tài khoản thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy tài khoản"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Trùng số điện thoại với tài khoản khác"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> update(@PathVariable Long accountId, @RequestBody AccountUpdateRequest request);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa tài khoản QL chi nhánh
     * Chủ shop xóa tài khoản nhân viên cắt tóc
     * Chủ shop xóa tài khoản nhân viên massage
     * Chủ shop xóa tài khoản tiếp tân
     * start
     * */
    @DeleteMapping
    @PreAuthorize("hasAuthority('account:delete')")
    @Operation(summary = "Xóa tài khoản")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Xóa tài khoản thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy tài khoản"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Quản lý chi nhánh không được xóa tài khoản của tài khoản không thuộc chi nhánh mình quản lý"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> delete(@PathVariable Long accountId);
    /*
     * Use case
     * end
     * */

    /*
    * Use case
    *
    * start
    * */
    @GetMapping
    @PreAuthorize("hasAuthority('account:view-detail')")
    @Operation(summary = "Xem chi tiết tài khoản")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Xem chi tiết tài khoản thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy tài khoản"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> findById(@PathVariable Long accountId);
    /*
     * Use case
     * end
     * */
}
