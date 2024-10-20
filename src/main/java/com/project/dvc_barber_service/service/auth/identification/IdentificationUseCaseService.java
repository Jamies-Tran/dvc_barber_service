package com.project.dvc_barber_service.service.auth.identification;

import com.project.dvc_barber_service.dto.auth.identification.Identification;
import com.project.dvc_barber_service.dto.auth.identification.action.RefreshTokenAction;
import com.project.dvc_barber_service.dto.auth.identification.action.VerifyIdentificationAction;
import com.project.dvc_barber_service.service.auth.identification.usecase.IIdentificationUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentificationUseCaseService implements IIdentificationUseCase {
    @NonNull IdentificationService service;

    /**
     * UC1:
     * Xác thực và phân quyền
     **/

    /* 1-xác thực người dùng */
    @Override
    public Identification verifyIdentification(VerifyIdentificationAction action) {
        return service.verify(action);
    }
    /* 1-end */


    /* 2-refresh token */
    @Override
    public Identification refreshToken(RefreshTokenAction action) {
        return service.refreshToken(action);
    }
    /* 2-end */

    /**
     * UC1 - end
     **/
}
