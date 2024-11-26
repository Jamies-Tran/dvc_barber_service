package com.project.dvc_barber_service.controller.v1.barber.category;

import com.project.dvc_barber_service.config.handler.exception.ActionNotAllowException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.barber.category.models.BarberCategoryUpdateRequest;
import com.project.dvc_barber_service.controller.v1.barber.category.models.IBarberCategoryReqModelMapper;
import com.project.dvc_barber_service.controller.v1.barber.category.models.IBarberCategoryResModelMapper;
import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryDeleteAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryFindByIdAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryUpdateAction;
import com.project.dvc_barber_service.service.barber.category.usecase.IBarberCategoryUseCase;
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
public class BarberCategoryPathController implements IBarberCategoryPathAPI {
    @NonNull IBarberCategoryUseCase useCase;

    @NonNull IBarberCategoryReqModelMapper reqModelMapper;

    @NonNull IBarberCategoryResModelMapper resModelMapper;

    /*
     * Use case
     * Chủ shop xem chi tiết DM dịch vụ trong hệ thống
     * QL chi nhánh xem chi tiết DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem chi tiết DM dịch vụ trong hệ thống
     * Tiếp tân xem chi tiết DM dịch vụ trong hệ thống
     * Khách hàng xem chi tiết DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    public ResponseEntity<?> findById(Long barberCategoryId) {
        try {
            BarberCategoryFindByIdAction action = BarberCategoryFindByIdAction.buildFrom(barberCategoryId);
            BarberCategory foundBarberCategory = useCase.findById(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(foundBarberCategory),
                    "Tìm thấy DM dịch vụ"));
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

    /*
     * Use case
     * Chủ shop cập nhật DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    public ResponseEntity<?> update(Long barberCategoryId, BarberCategoryUpdateRequest request) {
        try {
            BarberCategory barberCategory = reqModelMapper.toDto(request);
            BarberCategoryUpdateAction action = BarberCategoryUpdateAction.buildFrom(barberCategoryId, barberCategory);
            BarberCategory updatedBarberCategory = useCase.update(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(updatedBarberCategory),
                    "Cập nhật DM dịch vụ thành công"));
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

    /*
     * Use case
     * Chủ shop xóa DM dịch vụ trong hệ thông
     * start
     * */
    @Override
    public ResponseEntity<?> delete(Long barberCategoryId) {
        try {
            BarberCategoryDeleteAction action = BarberCategoryDeleteAction.buildFrom(barberCategoryId);
            useCase.delete(action);

            return ResponseEntity.ok(ValueResponse.success(null,
                    "Xóa DM dịch vụ thành công"));
        } catch (ActionNotAllowException | ResourceNotFoundException e) {
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
