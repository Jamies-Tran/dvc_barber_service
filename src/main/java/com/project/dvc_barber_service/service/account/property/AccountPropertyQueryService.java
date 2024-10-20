package com.project.dvc_barber_service.service.account.property;

import com.project.dvc_barber_service.dto.account.property.AccountProperty;
import com.project.dvc_barber_service.dto.account.property.IAccountPropertyMapper;
import com.project.dvc_barber_service.dto.account.property.action.AccountPropFindByAccountIdAction;
import com.project.dvc_barber_service.repository.account.properity.IAccountPropertyRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountPropertyQueryService {
    @NonNull IAccountPropertyRepository repository;

    @NonNull IAccountPropertyMapper mapper;

    public Optional<AccountProperty> findByAccountId(AccountPropFindByAccountIdAction action) {
        return repository.findByAccountId(action.accountId())
                .map(mapper::toDto);
    }
}
