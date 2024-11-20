package com.project.dvc_barber_service.service.account;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountDeleteAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByIdAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.account.action.AccountSearchCriteria;
import com.project.dvc_barber_service.dto.account.action.AccountUpdateAction;
import com.project.dvc_barber_service.dto.account.action.AccountUpdatePasswordAction;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.service.account.usecase.IAccountUseCase;
import com.project.dvc_barber_service.service.auth.role.RoleQueryService;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService implements IAccountUseCase {
    @NonNull AccountCommandService commandService;

    @NonNull AccountQueryService queryService;

    @NonNull RoleQueryService roleQueryService;

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
    @Override
    @Transactional
    public Account save(AccountCreateAction action) {

        return commandService.save(action);
    }
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
    @Override
    @Transactional
    public Account update(AccountUpdateAction action) {
        return commandService.update(action);
    }
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
    @Override
    @Transactional
    public void delete(AccountDeleteAction action) {
        commandService.delete(action);
    }
    /*
     * Use case
     * end
     * */

    @Override
    public Boolean existAccountByPhone(AccountFindByPhoneAction action) {
        return queryService.findByPhone(action).isPresent();
    }

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
    public Page<Account> findAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        Page<Account> accounts = queryService.findAll(searchCriteria, pageRequestCustom);
        List<Long> roleIds = accounts.stream().map(Account::roleId).toList();

        Map<Long, Role> roles = roleQueryService
                .findByIds(roleIds)
                .stream()
                .collect(Collectors.toMap(Role::roleId, x -> x));

        return accounts.map(x -> x.withRole(roles.get(x.roleId()))
        );
    }
    /*
     * Use case
     * end
     * */

    /*
     * Use case
     * Chủ shop xem chi tiết tài khoản trong hệ thống
     * QL chi nhánh xem chi tiết tài khoản trong hệ thống
     * Nhân viên cắt tóc xem chi tiết tài khoản trong hệ thống
     * Nhân viên massage xem chi tiết tài khoản trong hệ thống
     * Nhân viên tiếp tân xem chi tiết tài khoản trong hệ thống
     * start
     * */
    @Override
    public Account findAccountById(AccountFindByIdAction action) {
        return queryService.findById(action);
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
    @Transactional
    public Account updatePassword(AccountUpdatePasswordAction action) {
        return commandService.updatePassword(action);
    }
    /*
     * Use case
     * end
     * */
}
