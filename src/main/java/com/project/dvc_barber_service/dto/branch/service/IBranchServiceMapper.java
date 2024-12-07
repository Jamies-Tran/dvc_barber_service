package com.project.dvc_barber_service.dto.branch.service;

import com.project.dvc_barber_service.repository.database.branch.service.BranchServiceEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchServiceMapper extends IObjectMapper<BranchService, BranchServiceEntity> {
}
