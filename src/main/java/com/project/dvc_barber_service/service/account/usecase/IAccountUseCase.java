package com.project.dvc_barber_service.service.account.usecase;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;

public interface IAccountUseCase {
    /*
     * UC2|UC3|UC4|UC5|UC20|UC21|UC22:
     * Tạo tài khoản quản lý chi nhánh + nhân viên cắt tóc/chuyên viên massage + tiếp tân
     ***/
    Account save(AccountCreateAction action);
    /*
     * UC2|UC3|UC4|UC5|UC20|UC21|UC22-end
     ***/

    /*1-Kiểm tra số điện thoại đã được đăng ký chưa*/
    Boolean existAccountByPhone(AccountFindByPhoneAction action);
    /*1-end*/
}
