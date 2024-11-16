package com.project.dvc_barber_service.service.account;

import com.project.dvc_barber_service.config.context.RequestContext;
import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceForbiddenException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.account.IAccountMapper;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountDeleteAction;
import com.project.dvc_barber_service.dto.account.action.AccountUpdateAction;
import com.project.dvc_barber_service.dto.account.action.AccountUpdatePasswordAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleFindByCodeAction;
import com.project.dvc_barber_service.dto.media.Media;
import com.project.dvc_barber_service.enums.expertise.EExpertise;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.enums.status.account.EAccountStatus;
import com.project.dvc_barber_service.enums.status.EDeleteStatus;
import com.project.dvc_barber_service.repository.database.account.AccountEntity;
import com.project.dvc_barber_service.repository.database.account.IAccountRepository;
import com.project.dvc_barber_service.service.auth.role.RoleQueryService;
import com.project.dvc_barber_service.util.PrepareSaveOrUpdate;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
     * Use case
     * Chủ shop tạo tài khoản QL chi nhánh
     * Chủ shop tạo tài khoản nhân viên cắt tóc trong chi nhánh
     * Chủ shop tạo tài khoản nhân viên massage trong chi nhánh
     * Chủ shop tạo tài khoản tiếp tân trong chi nhánh
     *
     * QL chi nhánh tạo tài khoản nhân viên cắt tóc
     * QL chi nhánh tạo tài khoản nhân viên massage
     * QL chi nhánh tạo tài khoản tiếp tân trong
     *
     * Khách hàng tạo tài khoản
     * start
     ***/
    public Account save(AccountCreateAction action) {
        String password = Objects.requireNonNullElse(action.account().password(), defaultPassword);
        Account account = action.account()
                .withPassword(passwordEncoder.encode(password));
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
            PrepareSaveOrUpdate.prepareSave(newAccount, accountLogin);
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
        return roleQueryService
                .findByCode(RoleFindByCodeAction.buildFrom(roleCode))
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phân quyền"));
    }

    private EAccountStatus prepareStatus(ERole role, Long branchId) {
        if(Objects.equals(role, ERole.SHOP_OWNER)
                || Objects.equals(role, ERole.CUSTOMER)) {
            return EAccountStatus.ENABLED;
        }

        return Objects.nonNull(branchId) ? EAccountStatus.ENABLED : EAccountStatus.DISABLED;
    }
    /*
     * Use case
     * end
     ***/

    /*
     * Use case
     * Chủ shop cập nhật thông tin tài khoản QL chi nhánh
     * Chủ shop cập nhật thông tin tài khoản nhân viên cắt tóc
     * Chủ shop cập nhật thông tin tài khoản nhân viên massage
     * Chủ shop cập nhật thông tin tài khoản tiếp tân
     *
     * QL chi nhánh cập nhật tài khoản nhân viên cắt tóc
     * QL chi nhánh cập nhật tài khoản nhân viên massage
     * QL chi nhánh cập nhật tài khoản tiếp tân
     *
     * Chủ Shop cập nhật thông tin tài khoản
     * QL chi nhánh cập nhật thông tin tài khoản
     * Nhân viên cắt tóc cập nhật thông tin tài khoản
     * Nhân viên massage cập nhật thông tin tài khoản
     * Nhân viên tiếp tân cập nhật thông tin tài khoản
     *
     * Khách hàng cập nhật thông tin tài khoản
     * start
     * */
    public Account update(AccountUpdateAction action) {
        try {
            Account account = action.account();
            Long accountId = Objects.requireNonNullElse(action.accountId(), requestContext.getAccount().accountId());
            Optional<AccountEntity> tryToGetAccount = repository.findById(accountId);
            return tryToGetAccount.map(x -> {
                if(!Objects.equals(x.getPhone(), action.account().phone()) && repository.existsByPhone(account.phone())) {
                    throw new ResourceConflictException("Số điện thoại đã tồn tại");
                }
                byte[] openingImage = prepareOpeningBeforeUpdate(account.openingImageMedia());
                mapper.update(x, account, openingImage);
                PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());
                AccountEntity newAccount = repository.save(x);
                return mapper.toDto(newAccount);
            }).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));

        } catch (ResourceNotFoundException | ResourceConflictException e) {
             throw e;
        } catch (Exception e) {
            log.error("[{}-update] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }

    private byte[] prepareOpeningBeforeUpdate(List<Media> media) {
        if(Objects.isNull(media) || media.isEmpty()) {
            return null;
        }

        return AppObjectMapper.convertDataToByte(media);
    }
    /*
     * Use case
     * end
     * */

    /*
    * Use case
    * Chủ shop xóa tài khoản QL chi nhánh
    * Chủ shop xóa tài khoản nhân viên cắt tóc
    * Chủ shop xóa tài khoản nhân viên massage
    * Chủ shop xóa tài khoản tiếp tân
    * start
    * */
    public void delete(AccountDeleteAction action) {
        try {
            Optional<AccountEntity> tryToGetAccount = repository.findById(action.accountId());
            tryToGetAccount.ifPresentOrElse(
                    x -> {
                        validateDelete(x);
                        PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());
                        x.setStatusCode(EDeleteStatus.DELETED.getCode());
                        x.setStatusName(EDeleteStatus.DELETED.getName());
                        repository.save(x);
                    },
                    () -> {
                        throw new ResourceNotFoundException("Không tìm thấy tài khoản");
                    }
            );
        } catch (ResourceNotFoundException | ResourceForbiddenException e) {
          throw e;
        } catch (Exception e) {
            log.error("[{}-delete] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }

    private void validateDelete(AccountEntity deleteAccount) {
        if(Objects.equals(requestContext.getAccount().roleCode(), ERole.BRANCH_MANAGER.getCode())) {
            Long auditBranchId = requestContext.getAccount().branchId();
            if(!Objects.equals(auditBranchId, deleteAccount.getBranchId())) {
                throw new ResourceForbiddenException("Không có quyền để xóa tài khoản này");
            }
        }
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * QL chi nhánh cập nhật mật khẩu
     * Nhân viên cắt tóc cập nhật mật khẩu
     * Nhân viên massage cập nhật mật khẩu
     * Nhân viên tiếp tân cập nhật mật khẩu
     * Khách hàng cập nhật mật khẩu
     * start
     * */
    public Account updatePassword(AccountUpdatePasswordAction action) {
        try {
            return repository.findById(requestContext.getAccount().accountId())
                    .map(x -> {
                        if(!passwordEncoder.matches(action.oldPassword(), x.getPassword())) {
                            throw new ResourceForbiddenException("Mật khẩu cũ không đúng");
                        }
                        x.setPassword(passwordEncoder.encode(action.newPassword()));
                        PrepareSaveOrUpdate.prepareUpdate(x, requestContext.getAccount());
                        AccountEntity updateAccount = repository.save(x);
                        return mapper.toDto(updateAccount);
                    }).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));
        } catch (ResourceForbiddenException | ResourceNotFoundException e) {
          throw e;
        } catch (Exception e) {
            log.error("[{}-updatePassword] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
    /*
    * Use case
    * end
    * */
}
