package com.project.dvc_barber_service.repository.cache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMyCacheRepository extends JpaRepository<MyCacheEntity, Long> {

    @Query("""
        SELECT mc
        FROM MyCacheEntity mc
        WHERE mc.cacheKey = :cacheKey
    """)
    List<MyCacheEntity> findRefreshToken(String cacheKey);
}
