package com.project.dvc_barber_service.service.expertise;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.dto.expertise.IExpertiseMapper;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseFindByIdAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseSearchCriteria;
import com.project.dvc_barber_service.repository.database.expertise.IExpertiseRepository;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertiseQueryService {
    @NonNull IExpertiseRepository repository;

    @NonNull IExpertiseMapper mapper;

    /*
     * Use case
     * Chủ shop xem chi tiết DM chuyên môn
     * QL chi nhánh xem chi tiết DM chuyên môn
     * start
     * */
    public Expertise findById(ExpertiseFindByIdAction action) {
        return repository.findByExpertiseId(action.expertiseId())
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục chuyên môn"));
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem ds DM chuyên môn
     * QL chi nhánh xem ds DM chuyên môn
     * start
     * */
    public Page<Expertise> findAll(ExpertiseSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
    /*
     * Use case
     * end
     * */

}
