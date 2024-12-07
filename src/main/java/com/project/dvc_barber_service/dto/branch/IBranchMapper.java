package com.project.dvc_barber_service.dto.branch;

import com.project.dvc_barber_service.repository.database.branch.BranchEntity;
import com.project.dvc_barber_service.util.map.struct.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchMapper extends IObjectMapper<Branch, BranchEntity> {
}
