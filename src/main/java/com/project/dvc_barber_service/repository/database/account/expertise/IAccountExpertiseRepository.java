package com.project.dvc_barber_service.repository.database.account.expertise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountExpertiseRepository extends JpaRepository<AccountExpertiseEntity, Long> {
}
