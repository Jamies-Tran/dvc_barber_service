package com.project.dvc_barber_service.service.barber.service;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.dto.barber.service.IBarberServiceMapper;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceFindByIdAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceSearchCriteria;
import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import com.project.dvc_barber_service.repository.database.barber.service.IBarberServiceRepository;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BarberProductQueryService {
    @NonNull IBarberServiceRepository repository;

    @NonNull IBarberServiceMapper mapper;

    /**/
    public BarberService findById(BarberServiceFindByIdAction action) {
        return repository.getByBarberServiceId(action.barberServiceId())
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy dịch vụ"));
    }
    /*
    * Use case
    * end
    * */

    /**/
    public Page<BarberService> findAll(BarberServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
    /*
     * Use case
     * end
     * */

    public List<BarberService> findAllByCategoryId(Long categoryId) {
        return repository.findAllByBarberCategoryIdAndStatusCodeNot(categoryId, EDeleteStatus.DELETED.getCode())
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<BarberService> findAllByCategoryIds(List<Long> categoryIds) {
        return repository.findAllByBarberCategoryIdInAndStatusCodeNot(categoryIds, EDeleteStatus.DELETED.getCode())
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
