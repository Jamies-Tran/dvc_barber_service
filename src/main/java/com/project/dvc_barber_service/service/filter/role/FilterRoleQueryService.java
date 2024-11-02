package com.project.dvc_barber_service.service.filter.role;

import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.enums.role.action.FilterRoleFindByNameAction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterRoleQueryService {

    public List<ERole> findAllByName(FilterRoleFindByNameAction action) {
        return ERole.getList(action.name());
    }

}
