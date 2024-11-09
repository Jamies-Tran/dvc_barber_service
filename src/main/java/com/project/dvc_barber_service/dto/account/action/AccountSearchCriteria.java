package com.project.dvc_barber_service.dto.account.action;

import com.project.dvc_barber_service.enums.status.EAccountStatus;
import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Builder
public record AccountSearchCriteria(
        String phone,
        String name,
        Long branchId,
        List<String> roleCodes,
        List<String> expertiseCodes,
        List<String> statusCodes
) {
    public Boolean hasPhoneEmpty() {
        return !StringUtils.hasText(phone);
    }

    public Boolean hasNameEmpty() {
        return !StringUtils.hasText(name);
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasRoleCodesEmpty() {
        return Objects.isNull(roleCodes) || roleCodes.isEmpty();
    }

    public Boolean hasExpertiseCodesEmpty() {
        return Objects.isNull(expertiseCodes) || expertiseCodes.isEmpty();
    }

    public Boolean hasStatusCodesEmpty() {
        return Objects.isNull(statusCodes) || statusCodes.isEmpty();
    }
}
