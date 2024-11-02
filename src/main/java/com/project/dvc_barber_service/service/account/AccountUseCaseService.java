package com.project.dvc_barber_service.service.account;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.account.property.AccountProperty;
import com.project.dvc_barber_service.dto.account.property.action.AccountPropCreateAction;
import com.project.dvc_barber_service.enums.expertise.EExpertise;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.service.account.property.AccountPropertyCommandService;
import com.project.dvc_barber_service.service.account.usecase.IAccountUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService implements IAccountUseCase {
    @NonNull AccountCommandService command;

    @NonNull AccountQueryService query;

    @NonNull AccountPropertyCommandService accountPropCommand;

    /*
     * UC2|UC3|UC4|UC5|UC20|UC21|UC22:
     * Tạo tài khoản quản lý chi nhánh + nhân viên cắt tóc/chuyên viên massage + tiếp tân
     ***/
    @Transactional
    @Override
    public Account save(AccountCreateAction action) {
        Account savedAccount = command.save(action);
        AccountProperty accountProperty = prepareAccountProperty(action.role(), action.expertise(), action.branchId(),
                savedAccount.accountId());
        AccountProperty savedAccountProperty = accountPropCommand
                .save(AccountPropCreateAction.buildFrom(accountProperty, action.role()));

        return savedAccount
                .withAccountProperty(savedAccountProperty);
    }

    private AccountProperty prepareAccountProperty(ERole role, EExpertise expertise, Long branchId, Long accountId) {
        switch (role) {
            case BRANCH_MANAGER, RECEPTIONIST -> {
                return AccountProperty.buildFrom(branchId, accountId, role.getDefaultRoleCode());
            }
            case SERVICE_STAFF -> {
                if(Objects.isNull(expertise)) {
                    throw new ResourceNotFoundException("Thiếu chuyên môn cho nhân viên dịch vụ");
                }
                return AccountProperty.buildFrom(branchId, accountId, role.getDefaultRoleCode(), expertise);
            }
            case CUSTOMER -> {
                return AccountProperty.buildFrom(accountId, role.getDefaultRoleCode());
            }
            case SHOP_OWNER -> {
                return AccountProperty.empty(accountId);
            }
            default -> {return null;}
        }
    }
    /*
     * UC2|UC3|UC4|UC5|UC20|UC21|UC22-end
     ***/

    @Override
    public Boolean existAccountByPhone(AccountFindByPhoneAction action) {
        return query.findByPhone(action).isPresent();
    }
}
