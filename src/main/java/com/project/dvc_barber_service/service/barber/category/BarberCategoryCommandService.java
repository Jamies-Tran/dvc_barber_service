package com.project.dvc_barber_service.service.barber.category;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.barber.category.BarberCategory;
import com.project.dvc_barber_service.dto.barber.category.IBarberCategoryMapper;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryCreateAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryDeleteAction;
import com.project.dvc_barber_service.dto.barber.category.action.BarberCategoryUpdateAction;
import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import com.project.dvc_barber_service.repository.database.barber.category.BarberCategoryEntity;
import com.project.dvc_barber_service.repository.database.barber.category.IBarberCategoryRepository;
import com.project.dvc_barber_service.util.PrepareSaveOrUpdate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BarberCategoryCommandService {
    @NonNull IBarberCategoryRepository repository;

    @NonNull IBarberCategoryMapper mapper;

    @NonNull RequestContext requestContext;

    /*
     * Use case
     * Chủ shop thêm mới DM dịch vụ trong hệ thống
     * start
     * */
    public BarberCategory save(BarberCategoryCreateAction action) {
        try {
            BarberCategoryEntity newBarberCategory = mapper.toEntity(action.barberCategory());
            PrepareSaveOrUpdate.prepareSave(newBarberCategory, requestContext.getAccount());
            BarberCategoryEntity savedBarberCategory = repository.save(newBarberCategory);

            return mapper.toDto(savedBarberCategory);
        } catch (Exception e) {
            log.error("[{}-save] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
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
    public BarberCategory update(BarberCategoryUpdateAction action) {
        try {
            return repository.findByBarberCategoryId(action.barberCategoryId())
                    .map(x -> {
                        mapper.update(x,  action.barberCategory());
                        PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());
                        BarberCategoryEntity savedBarberCategory = repository.save(x);

                        return mapper.toDto(savedBarberCategory);
                    }).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy DM dịch vụ"));
        } catch (Exception e) {
            log.error("[{}-update] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
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
    public void delete(BarberCategoryDeleteAction action) {
        try {
            repository.findByBarberCategoryId(action.barberCategoryId())
                    .ifPresentOrElse(
                            x -> {
                                x.setStatusCode(EDeleteStatus.DELETED.getCode());
                                x.setStatusName(EDeleteStatus.DELETED.getName());
                                PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());

                                repository.save(x);
                            },
                            () -> {
                                throw new ResourceNotFoundException("Không tìm thấy DM dịch vụ");
                            }
                    );
        } catch (Exception e) {
            log.error("[{}-delete] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
    /*
     * Use case
     * end
     * */

}
