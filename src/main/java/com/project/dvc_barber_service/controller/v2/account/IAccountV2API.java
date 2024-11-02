package com.project.dvc_barber_service.controller.v2.account;

import com.project.dvc_barber_service.controller.v2.account.models.AccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v2/account")
public interface IAccountV2API {
    /*
     * UC2:
     * Tạo tài khoản quản lý chi nhánh
     ***/
    @PostMapping
    ResponseEntity<?> save(@RequestBody AccountRequest request);
    /*
     * UC2-end
     ***/
}
