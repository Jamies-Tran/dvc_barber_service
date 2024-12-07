package com.project.dvc_barber_service.controller.v1.branch.models;

import com.project.dvc_barber_service.dto.branch.Branch;
import com.project.dvc_barber_service.util.map.struct.IModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchResModelMapper extends IModelMapper<BranchResponse, Branch> {
}
