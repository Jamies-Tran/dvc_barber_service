package com.project.dvc_barber_service.repository.cache;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.time.LocalDateTime;

@With
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_cache")
public class MyCacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cacheId;

    @Lob
    @Column(name = "data")
    byte[] data;

    @Column(name = "cache_key")
    String cacheKey;

    @Column(name = "expired_at")
    LocalDateTime expiredAt;
}
