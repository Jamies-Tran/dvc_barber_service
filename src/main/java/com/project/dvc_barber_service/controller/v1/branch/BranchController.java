package com.project.dvc_barber_service.controller.v1.branch;

import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.controller.v1.branch.models.BranchRequest;
import com.project.dvc_barber_service.controller.v1.branch.models.IBranchReqModelMapper;
import com.project.dvc_barber_service.controller.v1.branch.models.IBranchResModelMapper;
import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.action.BranchCreateAction;
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
public class BranchController implements IBranchAPI {
    @NonNull IBranchUseCase useCase;

    @NonNull IBranchReqModelMapper reqModelMapper;

    @NonNull IBranchResModelMapper resModelMapper;

    @Override
    public ResponseEntity<?> save(BranchRequest request) {
        try {
            Branch branch = reqModelMapper.toDto(request);
            BranchCreateAction action = BranchCreateAction.buildFrom(branch);
            Branch savedBranch = useCase.save(action);

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(savedBranch),
                    "Lưu chi nhánh thành công"));
        } catch (ResourceConflictException e) {
          throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
