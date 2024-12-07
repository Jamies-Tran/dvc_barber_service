package com.project.dvc_barber_service.controller.v1.branch;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.branch.models.IBranchResModelMapper;
import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.action.BranchFindByIdAction;
import com.project.dvc_barber_service.service.branch.usecase.IBranchUseCase;
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
public class BranchPathController implements IBranchPathAPI {
    @NonNull IBranchUseCase useCase;

    @NonNull IBranchResModelMapper resModelMapper;

    @Override
    public ResponseEntity<?> findById(Long branchId) {
        try {
            Branch branch = useCase.findById(BranchFindByIdAction.buildFrom(branchId));

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(branch),
                    "Tìm kiếm chi nhánh thành công"));
        } catch (ResourceNotFoundException e) {
          throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
