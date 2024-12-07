package com.project.dvc_barber_service.repository.database.branch;

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
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branches")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long branchId;

    @Column(name = "branch_code")
    String branchCode;

    @Column(name = "contact")
    String contact;

    @Column(name = "address")
    String address;

    @Column(name = "latitude")
    BigDecimal latitude;

    @Column(name = "longitude")
    BigDecimal longitude;

    @Column(name = "open_time")
    LocalTime openTime;

    @Column(name = "close_time")
    LocalTime closeTime;

    @Column(name = "capacity")
    Integer capacity;

    @Column(name = "status_code")
    String statusCode;

    @Column(name = "status_name")
    String statusName;


    @PostPersist
    private void postPersist() {
        if(Objects.isNull(statusCode) || Objects.isNull(statusName)) {
            statusCode = EObjectStatus.ACTIVATED.getCode();
            statusName = EObjectStatus.ACTIVATED.getName();
        }

        if(Objects.isNull(branchCode)) {
            branchCode = "BR_%s_%s".formatted(branchId,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        }
    }
}
