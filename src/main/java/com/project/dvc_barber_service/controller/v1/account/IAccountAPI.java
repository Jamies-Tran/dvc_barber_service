package com.project.dvc_barber_service.controller.v1.account;

import com.project.dvc_barber_service.controller.v1.account.models.AccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/account")

public interface IAccountAPI {
    /*
     * UC2|UC3|UC4|UC5|UC20|UC21|UC22:
     * Tạo tài khoản quản lý chi nhánh + nhân viên cắt tóc/chuyên viên massage + tiếp tân
     ***/
    @PostMapping
    @PreAuthorize("hasAnyAuthority({" +
                "'staff:create', " +
                "'receptionist:create'," +
                "'manager:create'" +
            "})")
    ResponseEntity<?> save(@RequestBody AccountRequest request);
    /*
     * UC2|UC3|UC4|UC5|UC20|UC21|UC22-end
     ***/
}
