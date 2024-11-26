package com.project.dvc_barber_service.repository.database.barber.service;

import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceSearchCriteria;
import com.project.dvc_barber_service.repository.database.barber.service.dao.BarberServiceDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBarberServiceRepository extends JpaRepository<BarberServiceEntity, Long> {
    @Query("""
        SELECT bs
        FROM BarberServiceEntity bs
        WHERE bs.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()} 
            AND bs.barberServiceId = :barberServiceId
    """)
    Optional<BarberServiceEntity> findByBarberServiceId(Long barberServiceId);

    @Query("""
        SELECT 
            bs.barberServiceId AS barberServiceId,
            bc.barberCategoryId AS barberCategoryId,
            bc.categoryCode AS categoryCode,
            bc.categoryName AS categoryName,
            bs.serviceCode AS serviceCode,
            bs.serviceName AS serviceName,
            bs.description AS description,
            bs.price AS price,
            bs.estimateDuration AS estimateDuration,
            bs.durationTypeCode AS durationTypeCode,
            bs.durationTypeName AS durationTypeName,
            bs.thumbnail AS thumbnail,
            bs.serviceImages AS serviceImages,
            bs.statusCode AS statusCode,
            bs.statusName AS statusName
        FROM BarberServiceEntity bs
        INNER JOIN BarberCategoryEntity bc ON bs.barberCategoryId = bc.barberCategoryId
        WHERE bs.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND bs.barberServiceId = :barberServiceId
    """)
    Optional<BarberServiceDAO> getByBarberServiceId(Long barberServiceId);

    @Query("""
        SELECT 
            bs.barberServiceId AS barberServiceId,
            bc.barberCategoryId AS barberCategoryId,
            bc.categoryCode AS categoryCode,
            bc.categoryName AS categoryName,
            bs.serviceCode AS serviceCode,
            bs.serviceName AS serviceName,
            bs.description AS description,
            bs.price AS price,
            bs.estimateDuration AS estimateDuration,
            bs.durationTypeCode AS durationTypeCode,
            bs.durationTypeName AS durationTypeName,
            bs.thumbnail AS thumbnail,
            bs.serviceImages AS serviceImages,
            bs.statusCode AS statusCode,
            bs.statusName AS statusName
        FROM BarberServiceEntity bs
        INNER JOIN BarberCategoryEntity bc ON bs.barberCategoryId = bc.barberCategoryId
        WHERE bs.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                OR (bs.serviceName ILIKE %:#{#searchCriteria.search()}%
                    OR bs.serviceCode ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.hasCategoryIdEmpty()} = TRUE
                OR bc.barberCategoryId = :#{#searchCriteria.categoryId()})
            AND (:#{#searchCriteria.hasPriceRangeEmpty()} = TRUE
                OR bs.price BETWEEN :#{#searchCriteria.priceFrom()} 
                    AND :#{#searchCriteria.priceTo()})
            AND (:#{#searchCriteria.hasEstimateDurationRangeEmpty()} = TRUE
                OR bs.estimateDuration BETWEEN :#{#searchCriteria.estimateDurationFrom()} 
                    AND :#{#searchCriteria.estimateDurationTo()})
            AND (:#{#searchCriteria.hasDurationTypeCodeEmpty()} = TRUE
                OR bs.durationTypeCode = :#{#searchCriteria.durationTypeCode()})
    """)
    Page<BarberServiceDAO> findAll(BarberServiceSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT COUNT(bs) > 0
        FROM BarberServiceEntity bs
        WHERE bs.statusCode = :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND bs.serviceName = :serviceName
    """)
    Boolean existsByServiceName(String serviceName);

    List<BarberServiceEntity> findAllByBarberCategoryIdAndStatusCodeNot(Long barberCategoryId, String statusCode);

    List<BarberServiceEntity> findAllByBarberCategoryIdInAndStatusCodeNot(List<Long> barberCategoryIds, String statusCode);
}
