package com.project.dvc_barber_service.controller.v1.expertise;

import com.project.dvc_barber_service.controller.v1.expertise.models.ExpertiseUpdateRequest;
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

@RequestMapping("/v1/expertise/{expertiseId}")
@Tag(name = "Expertise", description = "Quản lý danh mục chuyên môn")
public interface IExpertisePathAPI {
    /*
     * Use case
     * Chủ shop xem chi tiết DM chuyên môn
     * QL chi nhánh xem chi tiết DM chuyên môn
     * start
     * */
    @GetMapping
    @PreAuthorize("hasAuthority('expertise:view-detail')")
    @Operation(
            summary = "Xem chi tiết danh mục",
            description = """          
                        - Chủ shop xem chi tiết DM chuyên môn
                        - QL chi nhánh xem chi tiết DM chuyên môn
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "xem chi tiết danh mục chuyên môn thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy danh mục chuyên môn")
    })
    ResponseEntity<?> findById(@PathVariable Long expertiseId);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop cập nhật DM chuyên môn
     * start
     * */
    @PutMapping
    @PreAuthorize("hasAuthority('expertise:update')")
    @Operation(
            summary = "Cập nhật DM chuyên môn",
            description = """          
                        - Chủ shop cập nhật DM chuyên môn
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật DM chuyên môn thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy DM chuyên môn"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> update(@PathVariable Long expertiseId, @RequestBody ExpertiseUpdateRequest request);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa DM chuyên môn
     * start
     * */
    @DeleteMapping
    @PreAuthorize("hasAuthority('expertise:delete')")
    @Operation(
            summary = "Xóa DM chuyên môn",
            description = """          
                        - Chủ shop xóa DM chuyên môn
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Xóa DM chuyên môn thành công"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy DM chuyên môn"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống")
    })
    ResponseEntity<?> delete(@PathVariable Long expertiseId);
    /*
     * Use case
     * end
     * */
}
