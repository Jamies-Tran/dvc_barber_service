package com.project.dvc_barber_service.service.barber.service.usecase;

import com.project.dvc_barber_service.dto.barber.service.BarberService;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceCreateAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceDeleteAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceFindByIdAction;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceSearchCriteria;
import com.project.dvc_barber_service.dto.barber.service.action.BarberServiceUpdateAction;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface IBarberProductUseCase {
    /**/
    BarberService save(BarberServiceCreateAction action);
    /*
    * Use case
    * end
    * */

    /**/
    BarberService findById(BarberServiceFindByIdAction action);
    /*
     * Use case
     * end
     * */

    /**/
    Page<BarberService> findAll(BarberServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
    /*
     * Use case
     * end
     * */

    /**/
    BarberService update(BarberServiceUpdateAction action);
    /*
     * Use case
     * end
     * */

    /**/
    void delete(BarberServiceDeleteAction action);
    /*
     * Use case
     * end
     * */
}
