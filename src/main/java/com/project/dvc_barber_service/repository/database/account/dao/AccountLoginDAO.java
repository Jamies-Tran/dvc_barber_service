package com.project.dvc_barber_service.repository.database.account.dao;

public interface AccountLoginDAO {
    Long getAccountId();

    Long getBranchId();

    String getPhone();

    String getFullName();

    String getRoleCode();

    String getAccountCode();
}
