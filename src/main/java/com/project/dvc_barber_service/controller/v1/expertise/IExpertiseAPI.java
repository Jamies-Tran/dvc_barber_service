package com.project.dvc_barber_service.controller.v1.expertise;

import com.project.dvc_barber_service.controller.v1.expertise.models.ExpertiseRequest;
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

@RequestMapping("/v1/expertise")
@Tag(name = "Expertise", description = "Quản lý danh mục chuyên môn")
public interface IExpertiseAPI {

    /*
     * Use case
     * Chủ shop tạo DM chuyên môn
     * start
     * */
    @PostMapping
    @PreAuthorize("hasAuthority('expertise:create')")
    @Operation(
            summary = "Tạo danh mục chuyên môn",
            description = """          
                        - Chủ shop tạo DM chuyên môn
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo danh mục thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy quyền của tài khoản"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> save(@RequestBody ExpertiseRequest request);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem ds DM chuyên môn
     * QL chi nhánh xem ds DM chuyên môn
     * start
     * */
    @GetMapping
    @PreAuthorize("hasAuthority('expertise:view-list')")
    @Operation(
            summary = "Tìm kiếm danh sách DM chuyên môn",
            description = """          
                        - Chủ shop xem ds DM chuyên môn
                        - QL chi nhánh xem ds DM chuyên môn
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm kiếm ds DM chuyên môn thành công"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
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
