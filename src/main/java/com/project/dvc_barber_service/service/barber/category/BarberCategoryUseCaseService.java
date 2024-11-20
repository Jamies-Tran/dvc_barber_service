package com.project.dvc_barber_service.service.barber.category;

import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryCreateAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryDeleteAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryFindByIdAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategorySearchCriteria;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryUpdateAction;
import com.project.dvc_barber_service.service.barber.category.usecase.IBarberCategoryUseCase;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class BarberCategoryUseCaseService implements IBarberCategoryUseCase {
    @NonNull BarberCategoryCommandService commandService;

    @NonNull BarberCategoryQueryService queryService;

    /*
     * Use case
     * Chủ shop thêm mới DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    @Transactional
    public BarberCategory save(BarberCategoryCreateAction action) {
        return commandService.save(action);
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem chi tiết DM dịch vụ trong hệ thống
     * QL chi nhánh xem chi tiết DM dịch vụ trong hệ thống
     * Nhân viên phục vụ xem chi tiết DM dịch vụ trong hệ thống
     * Tiếp tân xem chi tiết DM dịch vụ trong hệ thống
     * Khách hàng xem chi tiết DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    public BarberCategory findById(BarberCategoryFindByIdAction action) {
        return queryService.findById(action);
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
    @Override
    public Page<BarberCategory> findAll(BarberCategorySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return queryService.findAll(searchCriteria, pageRequestCustom);
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop cập nhật DM dịch vụ trong hệ thống
     * start
     * */
    @Override
    @Transactional
    public BarberCategory update(BarberCategoryUpdateAction action) {
        return commandService.update(action);
    }

    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa DM dịch vụ trong hệ thông
     * start
     * */
    @Override
    @Transactional
    public void delete(BarberCategoryDeleteAction action) {
        commandService.delete(action);
    }
    /*
     * Use case
     * end
     * */
}
