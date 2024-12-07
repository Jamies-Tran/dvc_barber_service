package com.project.dvc_barber_service.repository.database.branch;

import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBranchRepository extends JpaRepository<BranchEntity, Long> {
    @Query("""
        SELECT COUNT(br) > 0
        FROM BranchEntity br
        WHERE br.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND br.address ILIKE :address
    """)
    Boolean existsByAddress(String address);

    @Query("""
        SELECT br
        FROM BranchEntity br
        WHERE br.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND br.branchId = :branchId
    """)
    Optional<BranchEntity> findByBranchId(Long branchId);
}
