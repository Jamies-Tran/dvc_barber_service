package com.project.dvc_barber_service.service.branch;

import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.action.BranchAddServiceAction;
import com.project.dvc_barber_service.dto.branch.action.BranchCreateAction;
import com.project.dvc_barber_service.dto.branch.action.BranchFindByIdAction;
import com.project.dvc_barber_service.dto.branch.service.BranchService;
import com.project.dvc_barber_service.service.branch.service.BranchServiceCommandService;
import com.project.dvc_barber_service.service.branch.usecase.IBranchUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchUseCaseService implements IBranchUseCase {
    @NonNull BranchCommandService commandService;

    @NonNull BranchQueryService queryService;

    @NonNull BranchServiceCommandService branchServiceCommandService;

    /*
     * Lưu chi nhánh
     * */
    @Override
    @Transactional
    public Branch save(BranchCreateAction action) {
        return commandService.save(action);
    }
    /*
     * Use case
     * end
     * */

    /*
     * Tìm kiếm chi tiết chi nhánh
     * */
    @Override
    public Branch findById(BranchFindByIdAction action) {
        return queryService.findById(action);
    }
    /*
     * Use case
     * end
     * */

    /*
     * Thêm dịch vụ vào chi nhánh
     * */
    @Override
    @Transactional
    public Branch addService(BranchAddServiceAction action) {
        Branch branch = queryService.findById(BranchFindByIdAction.buildFrom(action.branchId()));
        List<BranchService> branchServices = branchServiceCommandService
                .saveAll(action.branchId(), action.branchServices());

        return branch.withBranchServices(branchServices);
    }
    /*
     * Use case
     * end
     * */


}
