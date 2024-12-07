package com.project.dvc_barber_service.service.branch.usecase;

import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.action.BranchAddServiceAction;
import com.project.dvc_barber_service.dto.branch.action.BranchCreateAction;
import com.project.dvc_barber_service.dto.branch.action.BranchFindByIdAction;

public interface IBranchUseCase {
    /*
     * Lưu chi nhánh
     * */
    Branch save(BranchCreateAction action);
    /*
    * Use case
    * end
    * */

    /*
     * Tìm kiếm chi tiết chi nhánh
     * */
    Branch findById(BranchFindByIdAction action);
    /*
     * Use case
     * end
     * */

    /*
    * Thêm dịch vụ vào chi nhánh
    * */
    Branch addService(BranchAddServiceAction action);
    /*
     * Use case
     * end
     * */
}
