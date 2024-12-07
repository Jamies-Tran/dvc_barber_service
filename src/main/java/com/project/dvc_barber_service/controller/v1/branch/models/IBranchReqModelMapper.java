package com.project.dvc_barber_service.controller.v1.branch.models;

import com.project.dvc_barber_service.dto.branch.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchReqModelMapper {
    @Mapping(target = "openTime", expression = "java(toLocalTime(model.openTime()))")
    @Mapping(target = "closeTime", expression = "java(toLocalTime(model.closeTime()))")
    Branch toDto(BranchRequest model);

    default LocalTime toLocalTime(LocalDateTime date) {
        return date.toLocalTime();
    }
}
