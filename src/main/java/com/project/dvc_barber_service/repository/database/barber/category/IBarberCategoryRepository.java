package com.project.dvc_barber_service.repository.database.barber.category;

import com.project.dvc_barber_service.dto.barber.category.action.BarberCategorySearchCriteria;
import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBarberCategoryRepository extends JpaRepository<BarberCategoryEntity, Long> {
    @Query("""
        SELECT bc
        FROM BarberCategoryEntity bc
        WHERE bc.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND bc.barberCategoryId = :barberCategoryId
    """)
    Optional<BarberCategoryEntity> findByBarberCategoryId(Long barberCategoryId);

    @Query("""
        SELECT bc
        FROM BarberCategoryEntity bc
        WHERE bc.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                OR (bc.categoryName ILIKE %:#{#searchCriteria.search()}%
                    OR bc.categoryCode ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.hasCategoryCodesEmpty()} = TRUE
                OR bc.categoryCode IN :#{#searchCriteria.categoryCodes()})
    """)
    Page<BarberCategoryEntity> findAll(BarberCategorySearchCriteria searchCriteria, Pageable pageable);
}
