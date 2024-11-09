package com.project.dvc_barber_service.controller.v3.filter.status.account;

import com.project.dvc_barber_service.controller.v3.filter.status.account.models.AccountStatusResponse;
import com.project.dvc_barber_service.enums.status.action.FilterAccountStatusFindByNameAction;
import com.project.dvc_barber_service.service.filter.status.account.usecase.IFilterAccountStatusUseCase;
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
public class FilterAccountStatusController implements IFilterAccountStatusAPI {
    @NonNull IFilterAccountStatusUseCase useCase;

    @Override
    public ResponseEntity<?> filterAccountStatus(String search) {
        try {
            List<AccountStatusResponse> responses = useCase.findAllByName(FilterAccountStatusFindByNameAction.buildFrom(search))
                    .stream()
                    .map(AccountStatusResponse::buildFrom)
                    .toList();

            return ResponseEntity.ok(ListResponse.success(responses, "Tìm kiếm danh sách xác thực thành công"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ListResponse.handler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
