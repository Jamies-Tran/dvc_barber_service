package com.project.dvc_barber_service.service.account;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.account.IAccountMapper;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleFindByCodeAction;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.enums.status.EAccountStatus;
import com.project.dvc_barber_service.repository.account.AccountEntity;
import com.project.dvc_barber_service.repository.account.IAccountRepository;
import com.project.dvc_barber_service.service.auth.role.RoleQueryService;
import com.project.dvc_barber_service.util.PrepareSave;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService {
    @NonNull IAccountRepository repository;

    @NonNull IAccountMapper mapper;

    @NonNull RoleQueryService roleQueryService;

    @NonNull PasswordEncoder passwordEncoder;

    @NonNull RequestContext requestContext;

    @Value("${app.default-password}")
    @NonFinal
    String defaultPassword;

    /*
    * UC2:
    * Tạo tài khoản quản lý chi nhánh
    ***/
    public Account save(AccountCreateAction action) {
        Account account = action.account()
                .withPassword(passwordEncoder.encode(defaultPassword));
        try {
            if (repository.existsByPhone(account.phone())) {
                throw new ResourceConflictException("số điện thoại đã tồn tại");
            }
            EAccountStatus status = prepareStatus(action.role(), action.branchId());
            Role role = prepareRole(action.role().getCode());
            AccountEntity newAccount = mapper.toEntity(account)
                    .withRoleId(role.roleId())
                    .withStatusCode(status.getCode())
                    .withStatusName(status.getName());
            AccountLogin accountLogin = prepareAccountLogin(role.roleCode());
            PrepareSave.<AccountEntity>prepare(newAccount, accountLogin);
            AccountEntity savedAccount = repository.save(newAccount);

            return mapper.toDto(savedAccount)
                    .withRole(role);
        } catch (ResourceConflictException e) {
            log.error("[{}-create] thông tin tài khoản đã tồn tại: {}", this.getClass().getSimpleName(),
                    action.account().phone());
            throw e;
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

    private Role prepareRole(String roleCode) {
        try {
            return roleQueryService
                    .findByCode(RoleFindByCodeAction.buildFrom(roleCode))
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phân quyền"));
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }

    private EAccountStatus prepareStatus(ERole role, Long branchId) {
        if(Objects.equals(role, ERole.SHOP_OWNER)
                || Objects.equals(role, ERole.CUSTOMER)) {
            return EAccountStatus.ENABLED;
        }

        return Objects.nonNull(branchId) ? EAccountStatus.ENABLED : EAccountStatus.DISABLED;
    }
    /*
     * UC2-end
     ***/
}
