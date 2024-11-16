package com.project.dvc_barber_service.controller.v1.expertise;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.expertise.models.ExpertiseResponse;
import com.project.dvc_barber_service.controller.v1.expertise.models.ExpertiseUpdateRequest;
import com.project.dvc_barber_service.controller.v1.expertise.models.IExpertiseReqModelMapper;
import com.project.dvc_barber_service.controller.v1.expertise.models.IExpertiseResModelMapper;
import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseDeleteAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseFindByIdAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseUpdateAction;
import com.project.dvc_barber_service.service.expertise.usecase.IExpertiseUseCase;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertisePathController implements IExpertisePathAPI {
    @NonNull IExpertiseUseCase useCase;

    @NonNull IExpertiseResModelMapper resModelMapper;

    @NonNull IExpertiseReqModelMapper reqModelMapper;

    /*
     * Use case
     * Chủ shop xem chi tiết DM chuyên môn
     * QL chi nhánh xem chi tiết DM chuyên môn
     * start
     * */
    @Override
    public ResponseEntity<?> findById(Long expertiseId) {
        try {
            ExpertiseFindByIdAction action = ExpertiseFindByIdAction.buildFrom(expertiseId);
            Expertise expertise = useCase.findById(action);
            ExpertiseResponse response = resModelMapper.toModel(expertise);

            return ResponseEntity.ok(ValueResponse
                    .success(response, "Tìm thấy chi tiết danh mục chuyên môn"));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("[{}-findById] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop cập nhật DM chuyên môn
     * start
     * */
    @Override
    public ResponseEntity<?> update(Long expertiseId, @RequestBody ExpertiseUpdateRequest request) {
        try {
            Expertise expertise = reqModelMapper.toDto(request);
            ExpertiseUpdateAction action = ExpertiseUpdateAction.buildFrom(expertiseId, expertise);
            Expertise updateExpertise = useCase.update(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(updateExpertise),
                    "Cập nhật danh mục chuyên môn thành công"));
        } catch (ResourceNotFoundException e) {
          throw e;
        } catch (Exception e) {
            log.error("[{}-update] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa DM chuyên môn
     * start
     * */
    @Override
    public ResponseEntity<?> delete(Long expertiseId) {
        try {
            ExpertiseDeleteAction action = ExpertiseDeleteAction.buildFrom(expertiseId);
            useCase.delete(action);

            return ResponseEntity.ok(ValueResponse.success(null, "Xóa DM chuyên môn thành công"));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */
}
