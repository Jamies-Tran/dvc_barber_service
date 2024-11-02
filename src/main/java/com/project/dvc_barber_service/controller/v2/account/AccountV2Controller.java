package com.project.dvc_barber_service.controller.v2.account;


import com.project.dvc_barber_service.config.handler.exception.ResourceConflictException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.v2.account.models.AccountRequest;
import com.project.dvc_barber_service.controller.v2.account.models.IAccountReqModelMapper;
import com.project.dvc_barber_service.controller.v2.account.models.IAccountResModelMapper;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.enums.expertise.EExpertise;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.service.account.usecase.IAccountUseCase;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountV2Controller implements IAccountV2API {
    @NonNull IAccountUseCase useCase;

    @NonNull IAccountResModelMapper resModelMapper;

    @NonNull IAccountReqModelMapper reqModelMapper;


    @Override
    public ResponseEntity<?> save(AccountRequest request) {
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
}
