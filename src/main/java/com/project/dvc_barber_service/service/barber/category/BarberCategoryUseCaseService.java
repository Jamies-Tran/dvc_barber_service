package com.project.dvc_barber_service.service.barber.category;

import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryCreateAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryDeleteAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryFindByIdAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategorySearchCriteria;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryUpdateAction;
import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.service.barber.category.usecase.IBarberCategoryUseCase;
import com.project.dvc_barber_service.service.barber.service.BarberProductQueryService;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class BarberCategoryUseCaseService implements IBarberCategoryUseCase {
    @NonNull BarberCategoryCommandService commandService;

    @NonNull BarberCategoryQueryService queryService;

    @NonNull BarberProductQueryService productQueryService;

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
        BarberCategory category = queryService.findById(action);

        return category.withServices(prepareService(category.barberCategoryId()));
    }

    private List<BarberService> prepareService(Long categoryId) {
        return productQueryService.findAllByCategoryId(categoryId);
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
        Page<BarberCategory> categories = queryService.findAll(searchCriteria, pageRequestCustom);
        List<Long> categoryIds = categories.map(BarberCategory::barberCategoryId).stream().toList();
        Map<Long, List<BarberService>> services = prepareServiceForList(categoryIds);

        return categories.map(x ->
                x.withServices(services.computeIfAbsent(x.barberCategoryId(), _ -> new ArrayList<>())));
    }

    private Map<Long, List<BarberService>> prepareServiceForList(List<Long> categoryIds) {
        return productQueryService.findAllByCategoryIds(categoryIds)
                .stream()
                .collect(Collectors.groupingBy(BarberService::barberCategoryId));
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
