package com.project.dvc_barber_service.repository.database.account;

import com.project.dvc_barber_service.repository.database.audit.AuditEntity;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column(name = "branch_id")
    Long branchId;

    @Column(name = "account_code")
    String accountCode;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @With
    @Column(name = "gender_code")
    String genderCode;

    @With
    @Column(name = "gender_name")
    String genderName;

    @Column(name = "phone")
    String phone;

    @Column(name = "address")
    String address;

    @Column(name = "password")
    String password;

    @Column(name = "dob")
    LocalDateTime dob;

    @Column(name = "avatar")
    String avatar;

    @Lob
    @Column(name = "opening_image")
    byte[] openingImage;

    @With
    @Column(name = "expertise_code")
    String expertiseCode;

    @With
    @Column(name = "expertise_name")
    String expertiseName;

    @With
    @Column(name = "role_id")
    Long roleId;

    @With
    @Column(name = "status_code")
    String statusCode;

    @With
    @Column(name = "status_name")
    String statusName;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(openingImage)) {
            openingImage = AppObjectMapper.convertDataToByte(List.of());
        }
    }

    @PostPersist
    public void postPersist() {
        if(Objects.isNull(accountCode)) {
            accountCode = "ACC%s%s".formatted(phone,
                    this.getCreatedAt().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        }
    }
}
