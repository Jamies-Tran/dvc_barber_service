package com.project.dvc_barber_service.repository.database.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditEntity {
    @Column(name = "created_by_name")
    String createdByName;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "updated_by_name")
    String updatedByName;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
