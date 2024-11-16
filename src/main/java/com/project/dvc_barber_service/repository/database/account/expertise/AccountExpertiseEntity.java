package com.project.dvc_barber_service.repository.database.account.expertise;

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
@Table(name = "account_expertise")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountExpertiseEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountExpertiseId;

    @Column(name = "account_id")
    Long accountId;

    @Column(name = "expertise_id")
    Long expertiseId;
}
