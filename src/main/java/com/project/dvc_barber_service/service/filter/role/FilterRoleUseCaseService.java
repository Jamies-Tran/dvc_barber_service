package com.project.dvc_barber_service.service.filter.role;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.enums.role.action.FilterRoleFindByNameAction;
import com.project.dvc_barber_service.service.filter.role.usecase.IFilterRoleUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterRoleUseCaseService implements IFilterRoleUseCase {
    @NonNull FilterRoleQueryService queryService;

    @NonNull RequestContext requestContext;

    /*1-Bộ lọc xác thức*/
    @Override
    public List<ERole> findAllByName(FilterRoleFindByNameAction action) {
        Optional<AccountLogin> accountLogin = requestContext.tryToGetAccount();
        String roleCode = accountLogin.map(AccountLogin::roleCode).orElse(null);
        return queryService.findAllByName(action)
                .stream()
                .filter(x -> !ignoreByRole(roleCode).contains(x))
                .toList();
    }

    private List<ERole> ignoreByRole(String roleCode) {
        if(Objects.equals(ERole.SHOP_OWNER.getCode(), roleCode)) {
            return List.of(
                    ERole.SHOP_OWNER,
                    ERole.CUSTOMER);
        } else if(Objects.equals(ERole.BRANCH_MANAGER.getCode(), roleCode)) {
            return List.of(
                    ERole.SHOP_OWNER,
                    ERole.BRANCH_MANAGER,
                    ERole.CUSTOMER);
        } else {
            return ERole.getList();
        }
    }
    /*1-end*/
}
