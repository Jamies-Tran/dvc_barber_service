package com.project.dvc_barber_service.repository.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditEntity {
    @Column(name = "created_by")
    String createdBy;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
