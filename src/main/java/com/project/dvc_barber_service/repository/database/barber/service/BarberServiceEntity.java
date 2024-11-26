package com.project.dvc_barber_service.repository.database.barber.service;

import com.project.dvc_barber_service.enums.duration.EDurationType;
import com.project.dvc_barber_service.enums.status.object.EObjectStatus;
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
import org.hibernate.annotations.Type;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "barber_service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BarberServiceEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long barberServiceId;

    @Column(name = "barber_category_id")
    Long barberCategoryId;

    @Column(name = "service_code")
    String serviceCode;

    @Column(name = "service_name")
    String serviceName;

    @Column(name = "description")
    String description;

    @Column(name = "price")
    Long price;

    @Column(name = "estimate_duration")
    Integer estimateDuration;

    @With
    @Column(name = "duration_type_code")
    String durationTypeCode;

    @With
    @Column(name = "duration_type_name")
    String durationTypeName;

    @Column(name = "thumbnail")
    String thumbnail;

    @Lob
    @Column(name = "service_images")
    byte[] serviceImages;

    @Column(name = "status_code")
    String statusCode;

    @Column(name = "status_name")
    String statusName;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(serviceImages)) {
            serviceImages = AppObjectMapper.convertDataToByte(List.of());
        }
    }

    @PostPersist
    public void postPersist() {
        if(!StringUtils.hasText(statusCode) || !StringUtils.hasText(statusName)) {
            statusCode = EObjectStatus.ACTIVATED.getCode();
            statusName = EObjectStatus.ACTIVATED.getName();
        }

        if(!StringUtils.hasText(serviceCode)) {
            serviceCode = "BS_%s_%s".formatted(barberCategoryId.toString(), barberServiceId.toString());
        }

        if(Objects.isNull(estimateDuration)) {
            estimateDuration = 30;
        }

        if(!StringUtils.hasText(durationTypeCode) || !StringUtils.hasText(durationTypeName)) {
            durationTypeCode = EDurationType.MINUTE.getCode();
            durationTypeName = EDurationType.MINUTE.getName();
        }
    }
}
