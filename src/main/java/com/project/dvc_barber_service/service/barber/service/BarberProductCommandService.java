package com.project.dvc_barber_service.service.barber.service;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.dto.barber.service.IBarberServiceMapper;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceCreateAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceDeleteAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceUpdateAction;
import com.project.dvc_barber_service.enums.duration.EDurationType;
import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import com.project.dvc_barber_service.repository.database.barber.service.BarberServiceEntity;
import com.project.dvc_barber_service.repository.database.barber.service.IBarberServiceRepository;
import com.project.dvc_barber_service.util.PrepareSaveOrUpdate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BarberProductCommandService {
    @NonNull IBarberServiceRepository repository;

    @NonNull IBarberServiceMapper mapper;

    @NonNull RequestContext requestContext;

    /**/
    public BarberService save(BarberServiceCreateAction action) {
        try {
            BarberService barberService = action.barberService();
            if(repository.existsByServiceName(barberService.serviceName())) {
                throw new ResourceConflictException("Tên dịch vụ đã tồn tại");
            }
            EDurationType durationType = tryToGetDurationType(barberService.durationTypeCode());
            BarberServiceEntity newBarberService = mapper.toEntity(barberService)
                    .withDurationTypeCode(durationType.getCode())
                    .withDurationTypeName(durationType.getName());
            PrepareSaveOrUpdate.prepareSave(newBarberService, requestContext.getAccount());
            BarberServiceEntity savedBarberService = repository.save(newBarberService);

            return mapper.toDto(savedBarberService);
        } catch (ResourceConflictException | ResourceNotFoundException e) {
          throw e;
        } catch (Exception e) {
            log.error("[{}-save] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
    /*
    * Use case
    * end
    * */

    /**/
    public BarberService update(BarberServiceUpdateAction action) {
        try {
            BarberService newBarberService = action.barberService();
            AccountLogin accountLogin = requestContext.getAccount();
            return repository.findByBarberServiceId(action.barberServiceId())
                    .map(x -> {
                        if(!Objects.equals(x.getServiceName(), newBarberService.serviceName())
                            && repository.existsByServiceName(newBarberService.serviceName())) {
                            throw new ResourceConflictException("Tên dịch vụ đã tồn tại");
                        }
                        EDurationType durationType = tryToGetDurationType(newBarberService.durationTypeCode());
                        mapper.update(x, newBarberService);
                        x.setDurationTypeCode(durationType.getCode());
                        x.setDurationTypeName(durationType.getName());
                        PrepareSaveOrUpdate.prepareUpdate(x, accountLogin);
                        BarberServiceEntity savedBarberService = repository.save(x);

                        return mapper.toDto(savedBarberService);
                    }).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy dịch vụ"));
        } catch (ResourceNotFoundException | ResourceConflictException e) {
            throw e;
        } catch (Exception e) {
            log.error("[{}-update] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
    /*
     * Use case
     * end
     * */

    /**/
    public void delete(BarberServiceDeleteAction action) {
        try {
            AccountLogin accountLogin = requestContext.getAccount();
            repository.findByBarberServiceId(action.barberServiceId())
                    .ifPresentOrElse(
                            x -> {
                                x.setStatusCode(EDeleteStatus.DELETED.getCode());
                                x.setStatusName(EDeleteStatus.DELETED.getName());
                                PrepareSaveOrUpdate.prepareUpdate(x, accountLogin);

                                repository.save(x);
                            },
                            () -> {
                                throw new ResourceNotFoundException("Không tìm thấy dịch vụ");
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

    private EDurationType tryToGetDurationType(String durationTypeCode) {
        return EDurationType.getByCode(durationTypeCode)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy loại thời lượng"));
    }
}
