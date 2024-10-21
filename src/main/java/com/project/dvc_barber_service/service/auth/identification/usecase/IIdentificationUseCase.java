package com.project.dvc_barber_service.service.auth.identification.usecase;

import com.project.dvc_barber_service.dto.auth.identification.Identification;
import com.project.dvc_barber_service.dto.auth.identification.action.RefreshTokenAction;
import com.project.dvc_barber_service.dto.auth.identification.action.VerifyIdentificationAction;

public interface IIdentificationUseCase {
    /**
     * UC1:
     * Xác thực và phân quyền
     **/

    /* 1-xác thực người dùng*/
    Identification verifyIdentification(VerifyIdentificationAction action);
    /* 1-end */

    /* 2-refresh token */
    Identification refreshToken(RefreshTokenAction action);
    /* 2-end */

    /**
     * UC1 - end
     **/
}
