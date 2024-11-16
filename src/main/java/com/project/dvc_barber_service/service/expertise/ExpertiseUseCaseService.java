package com.project.dvc_barber_service.service.expertise;

import com.project.dvc_barber_service.dto.expertise.Expertise;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseCreateAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseDeleteAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseFindByIdAction;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseSearchCriteria;
import com.project.dvc_barber_service.dto.expertise.action.ExpertiseUpdateAction;
import com.project.dvc_barber_service.service.expertise.usecase.IExpertiseUseCase;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpertiseUseCaseService implements IExpertiseUseCase {
    @NonNull ExpertiseCommandService commandService;

    @NonNull ExpertiseQueryService queryService;

    /*
     * Use case
     * Chủ shop tạo DM chuyên môn
     * start
     * */
    @Override
    public Expertise save(ExpertiseCreateAction action) {
        return commandService.save(action);
    }
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
    @Override
    public Expertise findById(ExpertiseFindByIdAction action) {
        return queryService.findById(action);
    }
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
    @Override
    public Page<Expertise> findAll(ExpertiseSearchCriteria searchCriteria,
                                   PageRequestCustom pageRequestCustom) {
        return queryService.findAll(searchCriteria, pageRequestCustom);
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
    @Override
    public Expertise update(ExpertiseUpdateAction action) {
        return commandService.update(action);
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
    @Override
    public void delete(ExpertiseDeleteAction action) {
        commandService.delete(action);
    }
    /*
     * Use case
     * end
     * */
}
