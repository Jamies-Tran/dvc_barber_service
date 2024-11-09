package com.project.dvc_barber_service.controller.auth.authorization;

import com.project.dvc_barber_service.controller.auth.authorization.models.identification.IdentificationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth/authorization")
@Tag(name = "Authorization and authentication", description = "Xác thực và phân quyền")
public interface IAuthorizationAPI {

    /*
    * Use case
    * Xác thực và phân quyền
    * start
    * */
    @PostMapping("/login")
    @Operation(summary = "Đăng nhập")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Đăng nhập thành công"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Sai thông tin đăng nhập / Tài khoản chưa được kích hoạt"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thông")
    })
    ResponseEntity<?> verifyIdentification(@RequestBody IdentificationRequest identificationRequest);

    @GetMapping("/refresh/{refreshToken}")
    @Operation(summary = "Refresh xác thực")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Refresh xác thực thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy refresh token"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thông")
    })
    ResponseEntity<?> refreshToken(@PathVariable String refreshToken);
    /*
    * Use case
    * end
    * */
}
