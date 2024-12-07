package com.project.dvc_barber_service.repository.database.branch.service;

import com.project.dvc_barber_service.repository.database.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch_services")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchServiceEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long branchServiceId;

    @Column(name = "service_id")
    Long serviceId;

    @Column(name = "branch_id")
    Long branchId;

    @Column(name = "expertise_code")
    String expertiseCode;

    @Column(name = "price")
    Long price;
}
