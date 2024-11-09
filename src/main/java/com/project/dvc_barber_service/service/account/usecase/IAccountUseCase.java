package com.project.dvc_barber_service.service.account.usecase;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountDeleteAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByIdAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.account.action.AccountSearchCriteria;
import com.project.dvc_barber_service.dto.account.action.AccountUpdateAction;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface IAccountUseCase {
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
     *
     * Khách hàng tạo tài khoản
     * start
     ***/
    Account save(AccountCreateAction action);
    /*
     * Use case
     * end
     ***/

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
     *
     * Chủ Shop cập nhật thông tin tài khoản
     * QL chi nhánh cập nhật thông tin tài khoản
     * Nhân viên cắt tóc cập nhật thông tin tài khoản
     * Nhân viên massage cập nhật thông tin tài khoản
     * Nhân viên tiếp tân cập nhật thông tin tài khoản
     *
     * Khách hàng cập nhật thông tin tài khoản
     * start
     * */
    Account update(AccountUpdateAction action);
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
    void delete(AccountDeleteAction action);
    /*
     * Use case
     * end
     * */

    /*
    * Kiểm tra số điện thoại đã được đăng ký chưa
    * start
    * */
    Boolean existAccountByPhone(AccountFindByPhoneAction action);
    /*
     * end
     * */

    /*
     * Use case
     * Chủ shop xem danh tài khoản trong hệ thống
     * Chủ shop xem danh sách tài khoản trong chi nhánh
     * QL chi nhánh xem danh sách tài khoản trong chi nhánh
     * Nhân viên cắt tóc xem danh sách tài khoản trong chi nhánh
     * Nhân viên massage xem danh sách tài khoản trong chi nhánh
     * Nhân viên tiếp tân xem danh sách tài khoản trong chi nhánh
     * start
     * */
    Page<Account> findAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
    /*
     * Use case
     * end
     * */

    /*
    * Use case
    *
    * start
    * */
    Account findAccountById(AccountFindByIdAction action);
    /*
     * Use case
     * end
     * */

}
