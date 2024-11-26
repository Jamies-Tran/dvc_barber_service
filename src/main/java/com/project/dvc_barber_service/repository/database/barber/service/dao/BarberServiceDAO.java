package com.project.dvc_barber_service.repository.database.barber.service.dao;

public interface BarberServiceDAO {
    Long getBarberServiceId();

    Long getBarberCategoryId();

    String getCategoryCode();

    String getCategoryName();

    String getServiceCode();

    String getServiceName();

    String getDescription();

    Long getPrice();

    Integer getEstimateDuration();

    String getDurationTypeCode();

    String getDurationTypeName();

    String getThumbnail();

    byte[] getServiceImages();

    String getStatusCode();

    String getStatusName();
}
