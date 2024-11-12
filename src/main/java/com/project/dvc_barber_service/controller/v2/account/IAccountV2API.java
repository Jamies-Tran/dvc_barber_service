package com.project.dvc_barber_service.controller.v2.account;

import com.project.dvc_barber_service.controller.v2.account.models.AccountV2Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v2/account")
@Tag(name = "Account", description = "Quản lý tài khoản")
public interface IAccountV2API {
    /*
     * Use case
     * Khách hang tạo tài khoản
     * start
     ***/
    @PostMapping
    @Operation(
            summary = "Tạo tài khoản cho khách hàng",
            description = """
                    - Khách hang tạo tài khoản
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo tài khoản cho khách hàng thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy phân quyền của tài khoản"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Trùng số điện thoại với tài khoản khác"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> save(@RequestBody AccountV2Request request);
    /*
     * Use case
     * end
     ***/

    /*
     * Use case
     * Khách hàng cập nhật thông tin tài khoản
     * start
     ***/
    @PutMapping("/self-update")
    @PreAuthorize("hasAuthority('account:update')")
    @Operation(
            summary = "Cập nhật thông tin cá nhân",
            description = """
                    - Khách hàng cập nhật thông tin tài khoản
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thông tin cá nhân thành công"),
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
    ResponseEntity<?> selfUpdate(@RequestBody AccountV2Request request);
    /*
     * Use case
     * Khách hàng cập nhật thông tin tài khoản
     * start
     ***/

    /*
     * Use case
     * Khách hàng xem ds tài khoản trong hệ thống
     * start
     * */
    @GetMapping
    @PreAuthorize("hasAuthority('account:view-list')")
    @Operation(
            summary = "Tìm kiếm danh sách tài khoản cho khách hàng",
            description = """
                    - Khách hàng xem ds tài khoản trong hệ thống
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm kiếm danh sách tài khoản cho khách hàng thành công"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "phone", defaultValue = "") String phone,
            @RequestParam(required = false, value = "name", defaultValue = "") String name,
            @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
            @RequestParam(required = false, value = "roleCodes", defaultValue = "") List<String> roleCodes,
            @RequestParam(required = false, value = "expertiseCodes", defaultValue = "") List<String> expertiseCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "") String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize
    );
    /*
     * Use case
     * end
     * */
}
