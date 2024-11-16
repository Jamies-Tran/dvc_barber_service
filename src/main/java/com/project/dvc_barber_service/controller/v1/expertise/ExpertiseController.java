package com.project.dvc_barber_service.controller.v1.expertise;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.expertise.models.ExpertiseRequest;
import com.project.dvc_barber_service.controller.v1.expertise.models.ExpertiseResponse;
import com.project.dvc_barber_service.controller.v1.expertise.models.IExpertiseReqModelMapper;
import com.project.dvc_barber_service.controller.v1.expertise.models.IExpertiseResModelMapper;
import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseCreateAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseSearchCriteria;
import com.project.dvc_barber_service.service.expertise.usecase.IExpertiseUseCase;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import com.project.dvc_barber_service.util.response.PageResponse;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertiseController implements IExpertiseAPI {
    @NonNull IExpertiseUseCase useCase;

    @NonNull IExpertiseReqModelMapper reqModelMapper;

    @NonNull IExpertiseResModelMapper resModelMapper;


    /*
     * Use case
     * Chủ shop tạo DM chuyên môn
     * start
     * */
    @Override
    public ResponseEntity<?> save(ExpertiseRequest request) {
        try {
            ExpertiseCreateAction action = ExpertiseCreateAction.buildFrom(reqModelMapper.toDto(request));
            Expertise expertise = useCase.save(action);

            return ResponseEntity
                    .ok(ValueResponse.success(resModelMapper.toModel(expertise), "Tạo chuyên môn thành công"));
        } catch (ResourceNotFoundException e) {
          throw e;
        } catch (Exception e) {
            log.error("[{}-save] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /*
     * Use case
     * Chủ shop xem ds DM chuyên môn
     * QL chi nhánh xem ds DM chuyên môn
     * start
     * */
    @Override
    public ResponseEntity<?> findAll(String search,
                                     List<String> expertiseCodes,
                                     String sorter,
                                     Integer current,
                                     Integer pageSize) {
        try {
            ExpertiseSearchCriteria searchCriteria = ExpertiseSearchCriteria.buildFrom(search, expertiseCodes);
            PageRequestCustom pageRequestCustom = PageRequestCustom.buildFrom(current, pageSize, sorter);
            Page<ExpertiseResponse> responses = useCase.findAll(searchCriteria, pageRequestCustom)
                    .map(resModelMapper::toModel);

            return ResponseEntity.ok(PageResponse.success(responses,
                    " Tìm thấy ds danh mục chuyên môn"));
        } catch (Exception e) {
            log.error("[{}-findAll] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */
}
