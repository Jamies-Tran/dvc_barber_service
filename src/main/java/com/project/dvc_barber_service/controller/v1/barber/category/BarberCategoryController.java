package com.project.dvc_barber_service.controller.v1.barber.category;

import com.project.dvc_barber_service.controller.v1.barber.category.models.BarberCategoryRequest;
import com.project.dvc_barber_service.controller.v1.barber.category.models.BarberCategoryResponse;
import com.project.dvc_barber_service.controller.v1.barber.category.models.IBarberCategoryReqModelMapper;
import com.project.dvc_barber_service.controller.v1.barber.category.models.IBarberCategoryResModelMapper;
import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryCreateAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategorySearchCriteria;
import com.project.dvc_barber_service.service.barber.category.usecase.IBarberCategoryUseCase;
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
public class BarberCategoryController implements IBarberCategoryAPI {
    @NonNull IBarberCategoryUseCase useCase;

    @NonNull IBarberCategoryReqModelMapper reqModelMapper;

    @NonNull IBarberCategoryResModelMapper resModelMapper;

    /*
     * Use case
     * Chủ shop thêm mới DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    public ResponseEntity<?> save(BarberCategoryRequest request) {
        try {
            BarberCategory barberCategory = reqModelMapper.toDto(request);
            BarberCategoryCreateAction action = BarberCategoryCreateAction.buildFrom(barberCategory);
            BarberCategory savedBarberCategory = useCase.save(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(savedBarberCategory),
                    "Tạo DM dịch vụ thành công"));
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
     * Chủ shop xem danh sách DM dịch vụ trong hệ thống
     * QL chi nhánh xem danh sách DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem danh sách DM dịch vụ trong hệ thống
     * Tiếp tân xem danh sách DM dịch vụ trong hệ thống
     * Khách hàng xem danh sách DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    public ResponseEntity<?> findAll(String search,
                                     List<String> categoryCodes,
                                     String sorter,
                                     Integer current,
                                     Integer pageSize) {

        try {
            BarberCategorySearchCriteria searchCriteria = BarberCategorySearchCriteria.buildFrom(search, categoryCodes);
            PageRequestCustom pageRequestCustom = PageRequestCustom.buildFrom(current, pageSize, sorter);
            Page<BarberCategoryResponse> responses = useCase.findAll(searchCriteria, pageRequestCustom)
                    .map(resModelMapper::toModel);

            return ResponseEntity.ok(PageResponse.success(responses,
                    "Tìm thấy danh sách DM dịch vụ"));
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
