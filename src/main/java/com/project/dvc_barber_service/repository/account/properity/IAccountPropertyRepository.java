package com.project.dvc_barber_service.repository.account.properity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountPropertyRepository extends JpaRepository<AccountPropertyEntity, Long> {
    Optional<AccountPropertyEntity> findByAccountId(Long accountId);
}
