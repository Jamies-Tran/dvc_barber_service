package com.project.dvc_barber_service.controller.v1.barber.service;

import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.barber.service.models.BarberServiceUpdateRequest;
import com.project.dvc_barber_service.controller.v1.barber.service.models.IBarberServiceReqModelMapper;
import com.project.dvc_barber_service.controller.v1.barber.service.models.IBarberServiceResModelMapper;
import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceDeleteAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceFindByIdAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceUpdateAction;
import com.project.dvc_barber_service.service.barber.service.usecase.IBarberProductUseCase;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BarberServicePathController implements IBarberServicePathAPI {
    @NonNull IBarberProductUseCase useCase;

    @NonNull IBarberServiceReqModelMapper reqModelMapper;

    @NonNull IBarberServiceResModelMapper resModelMapper;

    @Override
    public ResponseEntity<?> findById(Long barberServiceId) {
        try {
            BarberServiceFindByIdAction action = BarberServiceFindByIdAction.buildFrom(barberServiceId);
            BarberService barberService = useCase.findById(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(barberService),
                    "Tìm thấy dịch vụ"));
        } catch (ResourceNotFoundException e) {
          throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @Override
    public ResponseEntity<?> update(Long barberServiceId, BarberServiceUpdateRequest request) {
        try {
            BarberService barberService = reqModelMapper.toDto(request);
            BarberServiceUpdateAction action = BarberServiceUpdateAction.buildFrom(barberServiceId, barberService);
            BarberService updated = useCase.update(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(updated),
                    "Cập nhật dịch vụ thành công"));
        } catch (ResourceConflictException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @Override
    public ResponseEntity<?> delete(Long barberServiceId) {
        try {
            BarberServiceDeleteAction action = BarberServiceDeleteAction.buildFrom(barberServiceId);
            useCase.delete(action);

            return ResponseEntity.ok(ValueResponse.success(null, "Xóa dịch vụ thành công"));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
