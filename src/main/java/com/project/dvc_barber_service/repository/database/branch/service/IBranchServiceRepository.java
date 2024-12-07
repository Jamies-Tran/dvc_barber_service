package com.project.dvc_barber_service.repository.database.branch.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBranchServiceRepository extends JpaRepository<BranchServiceEntity, Long> {
    List<BranchServiceEntity> findByBranchId(Long branchId);

    Boolean existsByServiceIdAndBranchId(Long serviceId, Long branchId);
}
