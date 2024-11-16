package com.project.dvc_barber_service.controller.v1.account;

import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceForbiddenException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v1.account.models.AccountRequest;
import com.project.dvc_barber_service.controller.v1.account.models.AccountResponse;
import com.project.dvc_barber_service.controller.v1.account.models.AccountUpdateRequest;
import com.project.dvc_barber_service.controller.v1.account.models.IAccountReqModelMapper;
import com.project.dvc_barber_service.controller.v1.account.models.IAccountResModelMapper;
import com.project.dvc_barber_service.controller.v1.account.models.UpdatePasswordRequest;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountSearchCriteria;
import com.project.dvc_barber_service.dto.account.action.AccountUpdateAction;
import com.project.dvc_barber_service.dto.account.action.AccountUpdatePasswordAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.service.account.usecase.IAccountUseCase;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import com.project.dvc_barber_service.util.response.PageResponse;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController implements IAccountAPI {
    @NonNull IAccountUseCase useCase;

    @NonNull IAccountResModelMapper resModelMapper;

    @NonNull IAccountReqModelMapper reqModelMapper;

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
     * start
     ***/
    @Override
    public ResponseEntity<?> save(AccountRequest request) {
        try {
            ERole role = ERole.getByCode(request.roleCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phân quyền của tài khoản"));
            Account account = reqModelMapper.toDto(request)
                    .withRole(Role.buildFrom(role));
            Account savedAccount = useCase.save(AccountCreateAction.buildFrom(account, role));

            return ResponseEntity.ok(ValueResponse.success(resModelMapper.toModel(savedAccount),
                    "Tạo tài khoản thành công."));
        } catch (ResourceNotFoundException | ResourceConflictException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    /*
     * Use case
     * end
     ***/

    /*
     * Use case
     * Chủ Shop cập nhật thông tin tài khoản
     * QL chi nhánh cập nhật thông tin tài khoản
     * Nhân viên cắt tóc cập nhật thông tin tài khoản
     * Nhân viên massage cập nhật thông tin tài khoản
     * Nhân viên tiếp tân cập nhật thông tin tài khoản
     * start
     * */
    @Override
    public ResponseEntity<?> selfUpdate(AccountUpdateRequest request) {
        try {
            Account account = reqModelMapper.toDto(request);
            Account updatedAccount = useCase.update(AccountUpdateAction.buildFrom(account));

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
     ***/

    /*
     * Use case
     * Chủ shop xem danh tài khoản trong hệ thống
     * Chủ shop xem danh sách tài khoản trong chi nhánh
     * QL chi nhánh xem danh sách tài khoản trong chi nhánh
     * QL chi nhánh xem danh sách tài khoản trong hệ thông
     * Nhân viên massage xem danh sách tài khoản trong chi nhánh
     * Nhân viên tiếp tân xem danh sách tài khoản trong chi nhánh
     * Nhân viên tiếp tân xem danh sách tài khoản trong hệ thống
     * Khách hàng xem danh sách tài khoản trong chi nhánh
     * Khách hàng xem danh sách tài khoản trong hệ thống
     * start
     * */
    @Override
    public ResponseEntity<?> findAll(String phone, String name, Long branchId,
                                     List<String> roleCodes, List<String> expertiseCodes, List<String> statusCodes,
                                     String sorter, Integer current, Integer pageSize) {

        try {
            AccountSearchCriteria searchCriteria = AccountSearchCriteria.builder()
                    .phone(phone)
                    .name(name)
                    .branchId(branchId)
                    .roleCodes(roleCodes)
                    .expertiseCodes(expertiseCodes)
                    .statusCodes(statusCodes)
                    .build();
            PageRequestCustom pageRequestCustom = PageRequestCustom.buildFrom(current, pageSize, sorter);
            Page<AccountResponse> responses = useCase.findAll(searchCriteria, pageRequestCustom)
                    .map(resModelMapper::toModel);

            return ResponseEntity.ok(PageResponse.success(responses, "Đã tìm thấy DS tài khoản."));
        } catch (Exception e) {
            log.error("[{}-findAll] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
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
     * QL chi nhánh cập nhật mật khẩu
     * Nhân viên cắt tóc cập nhật mật khẩu
     * Nhân viên massage cập nhật mật khẩu
     * Nhân viên tiếp tân cập nhật mật khẩu
     * Khách hàng cập nhật mật khẩu
     * start
     * */
    @Override
    public ResponseEntity<?> updatePassword(UpdatePasswordRequest request) {
        try {
            AccountUpdatePasswordAction action = AccountUpdatePasswordAction
                    .buildFrom(request.oldPassword(), request.newPassword());
            Account account = useCase.updatePassword(action);
            return ResponseEntity.ok(ValueResponse
                    .success(resModelMapper.toModel(account), "Thay đổi mật khẩu thành công"));
        } catch (ResourceForbiddenException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("[{}-updatePassword] Có lỗi xảy ra: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ValueResponse.handler("Có lỗi xảy ra", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    /*
     * Use case
     * end
     * */
}
