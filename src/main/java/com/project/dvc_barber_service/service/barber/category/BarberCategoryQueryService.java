package com.project.dvc_barber_service.service.barber.category;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.IBarberCategoryMapper;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryFindByIdAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategorySearchCriteria;
import com.project.dvc_barber_service.repository.database.barber.category.IBarberCategoryRepository;
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
public class BarberCategoryQueryService {
    @NonNull IBarberCategoryRepository repository;

    @NonNull IBarberCategoryMapper mapper;

    /*
     * Use case
     * Chủ shop xem chi tiết DM dịch vụ trong hệ thống
     * QL chi nhánh xem chi tiết DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem chi tiết DM dịch vụ trong hệ thống
     * Tiếp tân xem chi tiết DM dịch vụ trong hệ thống
     * Khách hàng xem chi tiết DM dịch vụ trong hệ thống
     * start
     * */
    public BarberCategory findById(BarberCategoryFindByIdAction action) {
        return repository.findByBarberCategoryId(action.barberCategoryId())
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy DM dịch vụ"));
    }
    /*
    * Use case
    * end
    * */

    /*
     * Use case
     * Chủ shop xem danh sách DM dịch vụ trong hệ thống
     * QL chi nhánh xem danh sách DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem danh sách DM dịch vụ trong hệ thống
     * Tiếp tân xem danh sách DM dịch vụ trong hệ thống
     * Khách hàng xem danh sách DM dịch vụ trong hệ thống
     * start
     * */
    public Page<BarberCategory> findAll(BarberCategorySearchCriteria searchCriteria,
                                        PageRequestCustom pageRequestCustom) {

        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
    /*
     * Use case
     * end
     * */
}
