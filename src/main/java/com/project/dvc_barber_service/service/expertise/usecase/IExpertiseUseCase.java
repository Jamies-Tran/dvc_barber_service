package com.project.dvc_barber_service.service.expertise.usecase;

import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseCreateAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseDeleteAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseFindByIdAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseSearchCriteria;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseUpdateAction;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface IExpertiseUseCase {
    /*
     * Use case
     * Chủ shop tạo DM chuyên môn
     * start
     * */
    Expertise save(ExpertiseCreateAction action);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem chi tiết DM chuyên môn
     * QL chi nhánh xem chi tiết DM chuyên môn
     * start
     * */
    Expertise findById(ExpertiseFindByIdAction action);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem ds DM chuyên môn
     * QL chi nhánh xem ds DM chuyên môn
     * start
     * */
    Page<Expertise> findAll(ExpertiseSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop cập nhật DM chuyên môn
     * start
     * */
    Expertise update(ExpertiseUpdateAction action);
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xóa DM chuyên môn
     * start
     * */
    void delete(ExpertiseDeleteAction action);
    /*
     * Use case
     * end
     * */
}
