package com.project.dvc_barber_service.service.barber.category.usecase;

import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryCreateAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryDeleteAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryFindByIdAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategorySearchCriteria;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryUpdateAction;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface IBarberCategoryUseCase {
    /*
     * Use case
     * Chủ shop thêm mới DM dịch vụ trong hệ thống
     * start
     * */
    BarberCategory save(BarberCategoryCreateAction action);
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
    BarberCategory findById(BarberCategoryFindByIdAction action);
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
    Page<BarberCategory> findAll(BarberCategorySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop cập nhật DM dịch vụ trong hệ thống
     * start
     * */
    BarberCategory update(BarberCategoryUpdateAction action);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa DM dịch vụ trong hệ thông
     * start
     * */
    void delete(BarberCategoryDeleteAction action);
    /*
     * Use case
     * end
     * */
}
