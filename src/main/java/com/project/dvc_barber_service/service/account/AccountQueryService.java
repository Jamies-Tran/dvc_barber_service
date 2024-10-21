package com.project.dvc_barber_service.service.account;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.IAccountMapper;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.account.property.AccountProperty;
import com.project.dvc_barber_service.dto.account.property.action.AccountPropFindByAccountIdAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleFindByIdAction;
import com.project.dvc_barber_service.repository.account.AccountEntity;
import com.project.dvc_barber_service.repository.account.IAccountRepository;
import com.project.dvc_barber_service.service.account.property.AccountPropertyQueryService;
import com.project.dvc_barber_service.service.auth.role.RoleQueryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull IAccountRepository repository;

    @NonNull IAccountMapper mapper;

    @NonNull RoleQueryService roleQueryService;

    @NonNull AccountPropertyQueryService accountPropertyQueryService;

    public Optional<Account> findByPhone(AccountFindByPhoneAction action) {
        Optional<AccountEntity> tryToGetAccount = repository.findByPhone(action.phone());
        if (tryToGetAccount.isPresent()) {
            AccountEntity account = tryToGetAccount.get();
            Role role = roleQueryService
                    .findById(RoleFindByIdAction.buildFrom(account.getRoleId()))
                    .orElse(null);
            AccountProperty property = accountPropertyQueryService
                    .findByAccountId(AccountPropFindByAccountIdAction.buildFrom(account.getAccountId()))
                    .orElse(null);
            return Optional.of(mapper.toDto(account)
                    .withAccountProperty(property)
                    .withRole(role));
        }

        return Optional.empty();
    }

}
