package com.project.dvc_barber_service.util;

import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.repository.database.audit.AuditEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class PrepareSaveOrUpdate<T extends AuditEntity> {
    public static <T extends AuditEntity> void prepareSave(T entity, AccountLogin accountLogin) {
        if(Objects.nonNull(accountLogin)) {
            entity.setCreatedByName(accountLogin.fullName());
            entity.setCreatedBy(accountLogin.phone());
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedByName(accountLogin.fullName());
            entity.setUpdatedBy(accountLogin.phone());
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity.setCreatedByName("default");
            entity.setCreatedBy("default");
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedByName("default");
            entity.setUpdatedBy("default");
            entity.setUpdatedAt(LocalDateTime.now());
        }

    }

    public static <T extends AuditEntity> void prepareUpdate(T entity, AccountLogin accountLogin) {
        if(Objects.nonNull(accountLogin)) {
            entity.setUpdatedByName(accountLogin.fullName());
            entity.setUpdatedBy(accountLogin.phone());
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity.setUpdatedByName("default");
            entity.setUpdatedBy("default");
            entity.setUpdatedAt(LocalDateTime.now());
        }
    }
}
