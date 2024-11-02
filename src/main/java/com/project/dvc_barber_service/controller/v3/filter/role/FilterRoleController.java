package com.project.dvc_barber_service.controller.v3.filter.role;

import com.project.dvc_barber_service.controller.v3.filter.role.models.FilterRoleResponse;
import com.project.dvc_barber_service.enums.role.action.FilterRoleFindByNameAction;
import com.project.dvc_barber_service.service.filter.role.usecase.IFilterRoleUseCase;
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
public class FilterRoleController implements IFilterRoleAPI {
    @NonNull IFilterRoleUseCase useCase;

    @Override
    public ResponseEntity<?> filterRole(String search) {
        try {
            List<FilterRoleResponse> responses = useCase.findAllByName(FilterRoleFindByNameAction.buildFrom(search))
                    .stream()
                    .map(FilterRoleResponse::buildFrom)
                    .toList();

            return ResponseEntity.ok(ListResponse.success(responses, "Tìm kiếm danh sách xác thực thành công"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ListResponse.handler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
