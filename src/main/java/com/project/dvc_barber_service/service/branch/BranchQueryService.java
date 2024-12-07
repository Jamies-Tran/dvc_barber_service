package com.project.dvc_barber_service.service.branch;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.dto.branch.IBranchMapper;
import com.project.dvc_barber_service.dto.branch.action.BranchFindByIdAction;
import com.project.dvc_barber_service.repository.database.branch.IBranchRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchQueryService {
    @NonNull IBranchRepository repository;

    @NonNull IBranchMapper mapper;

    /*
    * Tìm kiếm chi tiết chi nhánh
    * */
    public Branch findById(BranchFindByIdAction action) {
        return repository.findByBranchId(action.branchId())
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chi nhánh"));
    }
    /*
    * Use case
    * end
    * */
}
