package com.project.dvc_barber_service.service.account.property;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.account.property.AccountProperty;
import com.project.dvc_barber_service.dto.account.property.IAccountPropertyMapper;
import com.project.dvc_barber_service.dto.account.property.action.AccountPropCreateAction;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.repository.account.properity.AccountPropertyEntity;
import com.project.dvc_barber_service.repository.account.properity.IAccountPropertyRepository;
import com.project.dvc_barber_service.util.PrepareSave;
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
public class AccountPropertyCommandService {
    @NonNull IAccountPropertyRepository repository;

    @NonNull IAccountPropertyMapper mapper;

    @NonNull RequestContext requestContext;

    /*
     * UC2:
     * Tạo tài khoản quản lý chi nhánh
     ***/
    public AccountProperty save(AccountPropCreateAction action) {
        AccountProperty accountProperty = action.accountProperty();
        try {
            AccountPropertyEntity newAccountProp = mapper.toEntity(accountProperty);
            AccountLogin accountLogin = prepareAccountLogin(action.role().getCode());
            PrepareSave.<AccountPropertyEntity>prepare(newAccountProp, accountLogin);
            AccountPropertyEntity savedAccountProp = repository.save(newAccountProp);

            return mapper.toDto(savedAccountProp);
        } catch (Exception e) {
            log.error("[{}-create] có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }

    private AccountLogin prepareAccountLogin(String roleCode) {
        if(Objects.equals(roleCode, ERole.SHOP_OWNER.getCode())
                || Objects.equals(roleCode, ERole.CUSTOMER.getCode())) {
            return null;
        } else {
            return requestContext.getAccount();
        }
    }
    /*
     * UC2-end
     ***/
}
