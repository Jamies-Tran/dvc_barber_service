package com.project.dvc_barber_service.controller.authorization;

import com.project.dvc_barber_service.config.handler.exception.IdentificationException;
import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.controller.authorization.models.AuthorizeRequest;
import com.project.dvc_barber_service.controller.authorization.models.IRoleModelMapper;
import com.project.dvc_barber_service.controller.authorization.models.RoleResponse;
import com.project.dvc_barber_service.controller.authorization.models.identification.IIdentificationRequestMapper;
import com.project.dvc_barber_service.controller.authorization.models.identification.IIdentificationResponseMapper;
import com.project.dvc_barber_service.controller.authorization.models.identification.IRefreshTokenModelMapper;
import com.project.dvc_barber_service.controller.authorization.models.identification.IdentificationRequest;
import com.project.dvc_barber_service.dto.auth.identification.Identification;
import com.project.dvc_barber_service.dto.auth.identification.action.RefreshTokenAction;
import com.project.dvc_barber_service.dto.auth.identification.action.VerifyIdentificationAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateListAction;
import com.project.dvc_barber_service.service.auth.identification.usecase.IIdentificationUseCase;
import com.project.dvc_barber_service.service.auth.role.usecase.IRoleUseCase;
import com.project.dvc_barber_service.util.response.ListResponse;
import com.project.dvc_barber_service.util.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuppressWarnings("duplicate")
public class AuthorizationController implements IAuthorizationAPI {
    @NonNull IRoleUseCase roleUseCase;

    @NonNull IIdentificationUseCase useCase;

    @NonNull IRoleModelMapper modelMapper;

    @NonNull IIdentificationRequestMapper requestMapper;

    @NonNull IIdentificationResponseMapper responseMapper;

    @NonNull IRefreshTokenModelMapper refreshTokenModelMapper;

    @Override
    public ResponseEntity<?> save(AuthorizeRequest authRequest) {
        try {
            List<Role> roles = authRequest.roleRequests().stream()
                    .map(modelMapper::toDto)
                    .toList();
            List<RoleResponse> responseData = roleUseCase.save(RoleCreateListAction.buildFrom(roles))
                    .stream()
                    .map(modelMapper::toModel)
                    .toList();

            return ResponseEntity.ok(ListResponse.success(responseData, "Tạo quyền thành công."));
        } catch (Exception e) {
            log.error("[ROLE] Đã xảy ra lỗi: {}", e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ListResponse.handler("Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }

    @Override
    public ResponseEntity<?> verifyIdentification(IdentificationRequest identificationRequest) {
        try {
            VerifyIdentificationAction action = requestMapper.toAction(identificationRequest);
            Identification savedIdentification = useCase.verifyIdentification(action);

            return ResponseEntity.ok(ValueResponse.success(responseMapper.toModel(savedIdentification),
                    "Xác thực tài khoản thành công"));
        } catch (IdentificationException e) {
            log.error("[{}] Lỗi xác thực: {}", this.getClass().getSimpleName(), e.getMessage());
            throw new IdentificationException(e.getMessage());
        } catch (Exception e) {
            log.error("[{}-verifyIdentification] Lỗi hệ thống: {}", this.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(ListResponse.handler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @Override
    public ResponseEntity<?> refreshToken(String refreshToken) {
       try {
            Identification refreshIdentification = useCase.refreshToken(RefreshTokenAction.buildFrom(refreshToken));
            return ResponseEntity.ok(ValueResponse.success(refreshTokenModelMapper.from(refreshIdentification),
                    "refresh xác thực thành công"));
       } catch (ResourceNotFoundException e) {
           log.error("[{}-refreshToken] lỗi refresh token: {}", this.getClass().getSimpleName(), e.getMessage());
           throw new ResourceNotFoundException(e.getMessage());
       } catch (Exception e) {
           log.error("[{}-refreshToken] Lỗi hệ thống: {}", this.getClass().getSimpleName(), e.getMessage());
           return ResponseEntity.internalServerError()
                   .body(ListResponse.handler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
       }
    }
}
