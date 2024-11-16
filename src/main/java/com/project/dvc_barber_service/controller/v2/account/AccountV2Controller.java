package com.project.dvc_barber_service.controller.v2.account;


import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v2.account.models.AccountV2Request;
import com.project.dvc_barber_service.controller.v2.account.models.AccountV2Response;
import com.project.dvc_barber_service.controller.v2.account.models.IAccountReqV2ModelMapper;
import com.project.dvc_barber_service.controller.v2.account.models.IAccountResV2ModelMapper;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountSearchCriteria;
import com.project.dvc_barber_service.dto.account.action.AccountUpdateAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.enums.status.account.EAccountStatus;
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
public class AccountV2Controller implements IAccountV2API {
    @NonNull IAccountUseCase useCase;

    @NonNull
    IAccountResV2ModelMapper resModelMapper;

    @NonNull
    IAccountReqV2ModelMapper reqModelMapper;

    /*
     * Use case
     * Khách hang tạo tài khoản
     * start
     ***/
    @Override
    public ResponseEntity<?> save(AccountV2Request request) {
        try {
            ERole role = ERole.getByCode(ERole.CUSTOMER.getCode())
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
     * Khách hàng cập nhật thông tin tài khoản
     * start
     ***/
    @Override
    public ResponseEntity<?> selfUpdate(AccountV2Request request) {
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
     * Khách hàng xem ds tài khoản trong hệ thống
     * start
     * */
    @Override
    public ResponseEntity<?> findAll(String phone,
                                     String name,
                                     Long branchId,
                                     List<String> roleCodes,
                                     List<String> expertiseCodes,
                                     String sorter,
                                     Integer current,
                                     Integer pageSize) {

        try {
            AccountSearchCriteria searchCriteria = AccountSearchCriteria.builder()
                    .phone(phone)
                    .name(name)
                    .branchId(branchId)
                    .roleCodes(roleCodes)
                    .expertiseCodes(expertiseCodes)
                    .statusCodes(List.of(EAccountStatus.ENABLED.getCode()))
                    .build();
            PageRequestCustom pageRequestCustom = PageRequestCustom.buildFrom(current, pageSize, sorter);
            Page<AccountV2Response> responses = useCase.findAll(searchCriteria, pageRequestCustom)
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
     ***/
}
