package com.project.dvc_barber_service.controller.v1.branch.service;

import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.branch.models.IBranchResModelMapper;
import com.project.dvc_barber_service.controller.v1.branch.service.models.BranchServiceListRequest;
import com.project.dvc_barber_service.controller.v1.branch.service.models.IBranchServiceReqModelMapping;
import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.action.BranchAddServiceAction;
import com.project.dvc_barber_service.dto.branch.service.BranchService;
import com.project.dvc_barber_service.service.branch.usecase.IBranchUseCase;
import com.project.dvc_barber_service.util.response.ValueResponse;
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
public class BranchServiceController implements IBranchServiceAPI {
    @NonNull IBranchUseCase useCase;

    @NonNull IBranchServiceReqModelMapping resModelMapper;

    @NonNull IBranchResModelMapper branchResModelMapper;

    @Override
    public ResponseEntity<?> addService(Long branchId, BranchServiceListRequest request) {
        try {
            List<BranchService> branchServices = request.branchServices().stream()
                    .map(resModelMapper::toDto)
                    .toList();
            BranchAddServiceAction action = BranchAddServiceAction.buildFrom(branchId, branchServices);
            Branch branch = useCase.addService(action);

            return ResponseEntity.ok(ValueResponse.success(branchResModelMapper.toModel(branch),
                    "Thêm dịch vụ thành công"));
        } catch (ResourceNotFoundException | ResourceConflictException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
