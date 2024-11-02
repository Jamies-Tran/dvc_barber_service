package com.project.dvc_barber_service.service.filter.expertise;

import com.project.dvc_barber_service.enums.expertise.EExpertise;
import com.project.dvc_barber_service.enums.expertise.action.FilterExpertiseFindByNameAction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterExpertiseQueryService {
    public List<EExpertise> findAllByName(FilterExpertiseFindByNameAction action) {
        try {
            return EExpertise.getList(action.name());
        } catch (Exception e) {
            log.error("[{}-findAllByName] có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}
