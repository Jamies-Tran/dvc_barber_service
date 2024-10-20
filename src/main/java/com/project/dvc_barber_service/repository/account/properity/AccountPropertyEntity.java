package com.project.dvc_barber_service.repository.account.properity;

import com.project.dvc_barber_service.repository.audit.AuditEntity;
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
@Table(name = "account_properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountPropertyEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountPropertyId;

    @Column(name = "account_id")
    Long accountId;

    @Column(name = "branch_id")
    Long branchId;

    @Column(name = "account_code")
    String accountCode;
}
