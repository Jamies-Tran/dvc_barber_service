package com.project.dvc_barber_service.repository.database.expertise;

import com.project.dvc_barber_service.dto.expertise.action.ExpertiseSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExpertiseRepository extends JpaRepository<ExpertiseEntity, Long> {
    @Query("""
        SELECT e
        FROM ExpertiseEntity e
        WHERE e.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND e.expertiseId = :expertiseId
    """)
    Optional<ExpertiseEntity> findByExpertiseId(Long expertiseId);

    @Query("""
        SELECT e
        FROM ExpertiseEntity e
        WHERE e.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                OR e.expertiseName ILIKE %:#{#searchCriteria.search()}%)
            AND (:#{#searchCriteria.hasExpertiseCodesEmpty()} = TRUE
                OR e.expertiseCode IN :#{#searchCriteria.expertiseCodes()})
    """)
    Page<ExpertiseEntity> findAll(ExpertiseSearchCriteria searchCriteria, Pageable pageable);
}
