package com.project.dvc_barber_service.service.barber.service;

import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceCreateAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceDeleteAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceFindByIdAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceSearchCriteria;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceUpdateAction;
import com.project.dvc_barber_service.service.barber.service.usecase.IBarberProductUseCase;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BarberProductUseCaseService implements IBarberProductUseCase {
    @NonNull BarberProductCommandService commandService;

    @NonNull BarberProductQueryService queryService;

    /**/
    @Override
    @Transactional
    public BarberService save(BarberServiceCreateAction action) {
        return commandService.save(action);
    }
    /*
     * Use case
     * end
     * */

    /**/
    @Override
    public BarberService findById(BarberServiceFindByIdAction action) {
        return queryService.findById(action);
    }
    /*
     * Use case
     * end
     * */

    /**/
    @Override
    public Page<BarberService> findAll(BarberServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return queryService.findAll(searchCriteria, pageRequestCustom);
    }
    /*
     * Use case
     * end
     * */

    /**/
    @Override
    @Transactional
    public BarberService update(BarberServiceUpdateAction action) {
        return commandService.update(action);
    }
    /*
     * Use case
     * end
     * */

    /**/
    @Override
    @Transactional
    public void delete(BarberServiceDeleteAction action) {
        commandService.delete(action);
    }
    /*
     * Use case
     * end
     * */
}
