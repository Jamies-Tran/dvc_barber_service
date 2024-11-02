package com.project.dvc_barber_service.controller.v3.filter.expertise;

import com.project.dvc_barber_service.controller.v3.filter.expertise.models.FilterExpertiseResponse;
import com.project.dvc_barber_service.enums.expertise.action.FilterExpertiseFindByNameAction;
import com.project.dvc_barber_service.service.filter.expertise.usecase.IFilterExpertiseUseCase;
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
public class FilterExpertiseController implements IFilterExpertiseAPI {
    @NonNull IFilterExpertiseUseCase useCase;

    @Override
    public ResponseEntity<?> findAllByName(String search) {
        try {
            List<FilterExpertiseResponse> responses = useCase
                    .findAllByName(FilterExpertiseFindByNameAction.buildFrom(search))
                    .stream()
                    .map(FilterExpertiseResponse::buildFrom)
                    .toList();

            return ResponseEntity.ok(ListResponse.success(responses, "Tìm thấy chuyên môn thành công"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ListResponse.handler("Tìm thấy chuyên môn thành công",
                    HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
