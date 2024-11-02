package com.project.dvc_barber_service.repository.account;

import com.project.dvc_barber_service.repository.account.dao.AccountLoginDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByPhone(String phone);

    Boolean existsByPhone(String phone);

    @Query("""
        SELECT 
            a.accountId AS accountId,
            ap.branchId AS branchId,
            a.phone AS phone,
            CONCAT(a.firstName, ' ', a.lastName) AS fullName,
            r.roleCode AS roleCode,
            ap.accountCode AS accountCode
        FROM AccountEntity a
        LEFT JOIN AccountPropertyEntity ap ON a.accountId = ap.accountId
        LEFT JOIN RoleEntity r ON a.roleId = r.roleId
        WHERE a.phone = :phone
    """)
    Optional<AccountLoginDAO> findAccountLoginDAOByPhone(String phone);
}
