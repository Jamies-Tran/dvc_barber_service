package com.project.dvc_barber_service.repository.database.barber.category;

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
import org.springframework.util.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "barber_category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BarberCategoryEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long barberCategoryId;

    @Column(name = "category_code")
    String categoryCode;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "status_code")
    String statusCode;

    @Column(name = "status_name")
    String statusName;

    @PostPersist
    public void postPersist() {
        if(!StringUtils.hasText(statusCode) || !StringUtils.hasText(statusName)){
            statusCode = EObjectStatus.ACTIVATED.getCode();
            statusName = EObjectStatus.ACTIVATED.getName();
        }

        if(!StringUtils.hasText(categoryCode)){
            categoryCode = "BC-%s".formatted(barberCategoryId.toString());
        }
    }
}
