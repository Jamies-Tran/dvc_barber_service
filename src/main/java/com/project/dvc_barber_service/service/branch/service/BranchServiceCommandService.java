package com.project.dvc_barber_service.service.branch.service;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.branch.service.BranchService;
import com.project.dvc_barber_service.dto.branch.service.IBranchServiceMapper;
import com.project.dvc_barber_service.repository.database.branch.service.BranchServiceEntity;
import com.project.dvc_barber_service.repository.database.branch.service.IBranchServiceRepository;
import com.project.dvc_barber_service.util.PrepareSaveOrUpdate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchServiceCommandService {
    @NonNull IBranchServiceRepository repository;

    @NonNull IBranchServiceMapper mapper;

    @NonNull RequestContext requestContext;

    public List<BranchService> saveAll(Long branchId, List<BranchService> branchServices) {
        try {
            validateService(branchId, branchServices);
            AccountLogin accountLogin = requestContext.getAccount();
            List<BranchServiceEntity> existBranches = repository.findByBranchId(branchId);
            List<BranchServiceEntity> newBranchServices = branchServices.stream()
                    .map(x -> {
                        BranchServiceEntity newBranchService = mapper.toEntity(x);
                        PrepareSaveOrUpdate.prepareSave(newBranchService, accountLogin);

                        return newBranchService;
                    }).toList();

            return Stream.concat(existBranches.stream(), repository.saveAll(newBranchServices).stream())
                    .map(mapper::toDto)
                    .toList();
        } catch (ResourceConflictException e) {
            throw e;
        } catch (Exception e) {
            log.error("[{}-save] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }

    private void validateService(Long branchId, List<BranchService> branchServices) {
        for(BranchService branchService : branchServices) {
            if(repository.existsByServiceIdAndBranchId(branchService.serviceId(), branchId)) {
                throw new ResourceConflictException("Dịch vụ đã tồn tại trong chi nhánh");
            }
        }
    }
}
