package com.project.dvc_barber_service.controller.v1.branch.service.models;

import com.project.dvc_barber_service.dto.branch.service.BranchService;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchServiceReqModelMapping extends IModelMapper<BranchServiceRequest, BranchService> {
}
