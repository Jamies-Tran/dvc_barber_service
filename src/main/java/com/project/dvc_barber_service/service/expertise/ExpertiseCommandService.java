package com.project.dvc_barber_service.service.expertise;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.dto.expertise.IExpertiseMapper;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseCreateAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseDeleteAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseUpdateAction;
import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import com.project.dvc_barber_service.repository.database.expertise.ExpertiseEntity;
import com.project.dvc_barber_service.repository.database.expertise.IExpertiseRepository;
import com.project.dvc_barber_service.util.PrepareSaveOrUpdate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertiseCommandService {
    @NonNull IExpertiseRepository repository;

    @NonNull IExpertiseMapper mapper;

    @NonNull RequestContext requestContext;

    /*
     * Use case
     * Chủ shop tạo DM chuyên môn
     * start
     * */
    public Expertise save(ExpertiseCreateAction action) {
        try {
            ExpertiseEntity newExpertise = mapper.toEntity(action.expertise());
            PrepareSaveOrUpdate.prepareSave(newExpertise, requestContext.getAccount());
            ExpertiseEntity savedExpertise = repository.save(newExpertise);

            return mapper.toDto(savedExpertise);
        } catch (ResourceNotFoundException e) {
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

    /*
     * Use case
     * Chủ shop cập nhật DM chuyên môn
     * start
     * */
    public Expertise update(ExpertiseUpdateAction action) {
        try {
            return repository.findByExpertiseId(action.expertiseId())
                    .map(x -> {
                        mapper.update(x, action.expertise());
                        PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());
                        ExpertiseEntity savedExpertise = repository.save(x);

                        return mapper.toDto(savedExpertise);
                    }).orElseThrow(ResourceNotFoundException::new);
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
     * Chủ shop xóa DM chuyên môn
     * start
     * */
    public void delete(ExpertiseDeleteAction action) {
        try {
            repository.findByExpertiseId(action.expertiseId())
                    .ifPresentOrElse(
                            x -> {
                                x.setStatusCode(EDeleteStatus.DELETED.getCode());
                                x.setStatusName(EDeleteStatus.DELETED.getName());
                                PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());

                                repository.save(x);
                            },
                            () -> {
                                throw new ResourceNotFoundException("Không tìm thấy DM chuyên môn");
                            }
                    );
        } catch (Exception e) {
            log.error("[{}-delete] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
    /*
     * Use case
     *
     * start
     * */

}
