package com.project.dvc_barber_service.service.account;

import com.project.dvc_barber_service.config.handler.exception.ResourceNotFoundException;
import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.AccountLogin;
import com.project.dvc_barber_service.dto.account.IAccountMapper;
import com.project.dvc_barber_service.dto.account.action.AccountFindByIdAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.account.action.AccountSearchCriteria;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleFindByIdAction;
import com.project.dvc_barber_service.repository.database.account.IAccountRepository;
import com.project.dvc_barber_service.service.auth.role.RoleQueryService;
import com.project.dvc_barber_service.util.object.mapper.AppObjectMapper;
import com.project.dvc_barber_service.util.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull IAccountRepository repository;

    @NonNull IAccountMapper mapper;

    @NonNull RoleQueryService roleQueryService;

    /*1-tìm tài khoản bằng số điện thoại*/
    public Optional<Account> findByPhone(AccountFindByPhoneAction action) {
        return repository.findByPhone(action.phone())
                .map(x -> mapper.toDto(x).withRole(getRole(x.getRoleId())));
    }
    /*1-end*/

    /*2-tìm tài khoản được xác thực*/
    public Optional<AccountLogin> findAccountLoginByPhone(AccountFindByPhoneAction action) {
        return repository.findAccountLoginDAOByPhone(action.phone())
                .map(mapper::toDto);
    }
    /*2-end*/

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
    public Page<Account> findAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(x -> mapper.toDto(x, AppObjectMapper.convertImageList(x.getOpeningImage())));
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
    public Account findById(AccountFindByIdAction action) {
        return repository.findById(action.accountId())
                .map(x -> mapper.toDto(x)
                        .withRole(getRole(x.getRoleId()))
                        .withOpeningImageMedia(AppObjectMapper.convertImageList(x.getOpeningImage())))
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));
    }
    /*
    * Use case
    * end
    * */

    /*
    * Tìm xác thức của tài khoản
    * start
    * */
    private Role getRole(Long roleId) {
        return roleQueryService
                .findById(RoleFindByIdAction.buildFrom(roleId))
                .orElse(null);
    }
    /*end*/
}
