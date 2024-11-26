package com.project.dvc_barber_service.controller.v1.barber.service;

import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.barber.service.models.BarberServiceRequest;
import com.project.dvc_barber_service.controller.v1.barber.service.models.BarberServiceResponse;
import com.project.dvc_barber_service.controller.v1.barber.service.models.IBarberServiceReqModelMapper;
import com.project.dvc_barber_service.controller.v1.barber.service.models.IBarberServiceResModelMapper;
import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceCreateAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceSearchCriteria;
import com.project.dvc_barber_service.service.barber.service.usecase.IBarberProductUseCase;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import com.project.dvc_barber_service.util.response.PageResponse;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BarberServiceController implements IBarberServiceAPI {
    @NonNull IBarberProductUseCase useCase;

    @NonNull IBarberServiceReqModelMapper reqModelMapper;

    @NonNull IBarberServiceResModelMapper resModelMapper;

    /**/
    @Override
    public ResponseEntity<?> save(BarberServiceRequest request) {
        try {
            BarberService barberService = reqModelMapper.toDto(request);
            BarberServiceCreateAction action = BarberServiceCreateAction.buildFrom(barberService);
            BarberService savedBarberService = useCase.save(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(savedBarberService),
                    "Tạo dịch vụ thành công"));
        } catch (ResourceNotFoundException | ResourceConflictException e) {
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

    @Override
    public ResponseEntity<?> findAll(String search,
                                     String durationTypeCode,
                                     Long branchId,
                                     Long categoryId,
                                     List<Long> priceRange,
                                     List<Integer> estimateDurationRange,
                                     String sorter, Integer current, Integer pageSize) {
        try {
            BarberServiceSearchCriteria searchCriteria = BarberServiceSearchCriteria.buildFrom(
                    search,
                    durationTypeCode,
                    branchId,
                    categoryId,
                    priceRange,
                    estimateDurationRange);
            PageRequestCustom pageRequestCustom = PageRequestCustom.buildFrom(current, pageSize, sorter);
            Page<BarberServiceResponse> responses = useCase.findAll(searchCriteria, pageRequestCustom)
                    .map(resModelMapper::toModel);

            return ResponseEntity.ok(PageResponse.success(responses, "Tìm thấy danh sách dịch vụ"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
