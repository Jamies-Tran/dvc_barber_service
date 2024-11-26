package com.project.dvc_barber_service.dto.barber.service;

import com.project.dvc_barber_service.dto.media.Media;
import com.project.dvc_barber_service.repository.database.barber.service.BarberServiceEntity;
import com.project.dvc_barber_service.repository.database.barber.service.dao.BarberServiceDAO;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBarberServiceMapper extends IObjectMapper<BarberService, BarberServiceEntity> {
    @Mapping(target = "serviceImagesMedia", expression = "java(setServiceImagesMedia(dao.getServiceImages()))")
    BarberService toDto(BarberServiceDAO dao);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "serviceImages", expression = "java(setServiceImages(dto.serviceImagesMedia()))")
    void update(@MappingTarget BarberServiceEntity entity, BarberService dto);

    default List<Media> setServiceImagesMedia(byte[] serviceImages) {
        return AppObjectMapper.convertImageList(serviceImages);
    }

    default byte[] setServiceImages(List<Media> serviceImagesMedia) {
        return AppObjectMapper.convertDataToByte(serviceImagesMedia);
    }
}
