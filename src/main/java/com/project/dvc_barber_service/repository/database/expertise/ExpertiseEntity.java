package com.project.dvc_barber_service.repository.database.expertise;

import com.project.dvc_barber_service.enums.status.object.EObjectStatus;
import com.project.dvc_barber_service.repository.database.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expertises")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertiseEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long expertiseId;

    @Column(name = "expertise_code")
    String expertiseCode;

    @Column(name = "expertise_name")
    String expertiseName;

    @Column(name = "status_code")
    String statusCode;

    @Column(name = "status_name")
    String statusName;

    @PostPersist
    public void postPersist() {
        if(Objects.isNull(statusCode) || Objects.isNull(statusName)) {
            statusCode = EObjectStatus.ACTIVATED.getCode();
            statusName = EObjectStatus.ACTIVATED.getName();
        }
    }
}
