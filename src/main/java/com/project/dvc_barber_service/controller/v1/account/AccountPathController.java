package com.project.dvc_barber_service.controller.v1.account;

import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceForbiddenException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.account.models.AccountResponse;
import com.project.dvc_barber_service.controller.v1.account.models.AccountUpdateRequest;
import com.project.dvc_barber_service.controller.v1.account.models.IAccountReqModelMapper;
import com.project.dvc_barber_service.controller.v1.account.models.IAccountResModelMapper;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountDeleteAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByIdAction;
import com.project.dvc_barber_service.dto.account.action.AccountUpdateAction;
import com.project.dvc_barber_service.service.account.usecase.IAccountUseCase;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountPathController implements IAccountPathAPI {
    @NonNull IAccountUseCase useCase;

    @NonNull IAccountReqModelMapper reqModelMapper;

    @NonNull IAccountResModelMapper resModelMapper;

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
     * start
     * */
    @Override
    public ResponseEntity<?> update(Long accountId, AccountUpdateRequest request) {
        try {
            Account account = reqModelMapper.toDto(request);
            Account updatedAccount = useCase.update(AccountUpdateAction.buildFrom(accountId, account));

            return ResponseEntity.ok(ValueResponse
                    .success(resModelMapper.toModel(updatedAccount), "Cập nhật tài khoản thành công"));
        } catch (ResourceNotFoundException | ResourceConflictException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
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
    @Override
    public ResponseEntity<?> delete(Long accountId) {
        try {
            useCase.delete(AccountDeleteAction.buildFrom(accountId));

            return ResponseEntity.ok(ValueResponse.success(null, "Xóa tài khoản thành công"));
        } catch (ResourceNotFoundException | ResourceForbiddenException e) {
          throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     *
     * start
     * */
    @Override
    public ResponseEntity<?> findById(Long accountId) {
        try {
            Account account = useCase.findAccountById(AccountFindByIdAction.buildFrom(accountId));

            return ResponseEntity
                    .ok(ValueResponse.success(resModelMapper.toModel(account), "Tìm thấy tài khoản"));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("[{}-findById] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */
}
