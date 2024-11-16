package com.project.dvc_barber_service.repository.database.account;

import com.project.dvc_barber_service.dto.account.action.AccountSearchCriteria;
import com.project.dvc_barber_service.repository.database.account.dao.AccountLoginDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            a.branchId AS branchId,
            a.phone AS phone,
            CONCAT(a.firstName, ' ', a.lastName) AS fullName,
            r.roleCode AS roleCode,
            a.accountCode AS accountCode
        FROM AccountEntity a
        LEFT JOIN RoleEntity r ON a.roleId = r.roleId
        WHERE a.phone = :phone
    """)
    Optional<AccountLoginDAO> findAccountLoginDAOByPhone(String phone);

    @Query("""
        SELECT a
        FROM AccountEntity  a
        WHERE a.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()}
            AND a.accountId = :accountId
    """)
    Optional<AccountEntity> findByAccountId(Long accountId);

    @Query("""
        SELECT a
        FROM AccountEntity a
        INNER JOIN RoleEntity r ON a.roleId = r.roleId
        WHERE (:#{#searchCriteria.hasPhoneEmpty()} = TRUE
                OR a.phone ILIKE %:#{#searchCriteria.phone()}%)
            AND (:#{#searchCriteria.hasNameEmpty()} = TRUE
                OR (a.firstName ILIKE %:#{#searchCriteria.name()}%
                    OR a.lastName ILIKE %:#{#searchCriteria.name()}%))
            AND (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE
                OR a.branchId = :#{#searchCriteria.branchId()})
            AND (:#{#searchCriteria.hasStatusCodesEmpty()} = TRUE
                OR a.statusCode IN :#{#searchCriteria.statusCodes()})
            AND (:#{#searchCriteria.hasRoleCodesEmpty()} = TRUE
                OR r.roleCode IN :#{#searchCriteria.roleCodes()})
            AND (:#{#searchCriteria.hasExpertiseCodesEmpty()} = TRUE
                OR a.expertiseCode IN :#{#searchCriteria.expertiseCodes()})
            AND (a.statusCode != :#{T(com.project.dvc_barber_service.enums.status.EDeleteStatus).DELETED.getCode()})
    """)
    Page<AccountEntity> findAll(AccountSearchCriteria searchCriteria, Pageable pageable);
}
