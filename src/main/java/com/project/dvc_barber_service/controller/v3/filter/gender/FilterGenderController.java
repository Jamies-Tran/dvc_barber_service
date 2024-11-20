package com.project.dvc_barber_service.controller.v3.filter.gender;

import com.project.dvc_barber_service.controller.v3.filter.gender.models.GenderResponse;
import com.project.dvc_barber_service.enums.gender.action.FilterGenderAction;
import com.project.dvc_barber_service.service.filter.gender.usecase.IFilterGenderUseCase;
import com.project.dvc_barber_service.util.response.ListResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterGenderController implements IFilterGenderAPI {
    @NonNull IFilterGenderUseCase useCase;

    @Override
    public ResponseEntity<?> findAll(String search) {
        try {
            FilterGenderAction action = FilterGenderAction.buildFrom(search);
            List<GenderResponse> responses = useCase.findAll(action).stream()
                    .map(GenderResponse::buildFrom)
                    .toList();

            return ResponseEntity.ok(ListResponse.success(responses,
                    "Tìm danh sách giới tính thành công"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ListResponse.handler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
