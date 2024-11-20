package com.project.dvc_barber_service.config.init;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountCreateAction;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.dto.auth.permission.Permission;
import com.project.dvc_barber_service.dto.auth.role.Role;
import com.project.dvc_barber_service.dto.auth.role.action.RoleCreateListAction;
import com.project.dvc_barber_service.enums.gender.EGender;
import com.project.dvc_barber_service.enums.permission.EPermission;
import com.project.dvc_barber_service.enums.role.ERole;
import com.project.dvc_barber_service.service.account.usecase.IAccountUseCase;
import com.project.dvc_barber_service.service.auth.role.usecase.IRoleUseCase;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Initiation {
    @NonNull IRoleUseCase roleUseCase;

    @NonNull IAccountUseCase accountUseCase;

    @Value("${app.default-password}")
    @NonFinal
    String defaultPassword;

    @Value("${app.init-account.first-name}")
    @NonFinal
    String initAccountFirstName;

    @Value("${app.init-account.last-name}")
    @NonFinal
    String initAccountLastName;

    @Value("${app.init-account.phone}")
    @NonFinal
    String initAccountPhone;

    @PostConstruct
    public void initiation() {
        configInitRole();
        configAccount();
    }

    /*1-Khởi tạo phân quyền*/
    private void configInitRole() {
        List<Role> initRoleList = ERole.getList()
                .stream()
                .map(x -> Role.buildFrom(x).withPermissions(rolePermissionList(x)))
                .toList();

        roleUseCase.save(RoleCreateListAction.buildFrom(initRoleList));
    }

    private List<Permission> rolePermissionList(ERole role) {
        switch (role) {
            case SHOP_OWNER -> {
                return List.of(
                        Permission.buildFrom(EPermission.ACCOUNT_CREATE),
                        Permission.buildFrom(EPermission.ACCOUNT_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_DELETE),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_LIST),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_VIEW),
                        Permission.buildFrom(EPermission.BRANCH_CREATE),
                        Permission.buildFrom(EPermission.EXPERTISE_CREATE),
                        Permission.buildFrom(EPermission.EXPERTISE_UPDATE),
                        Permission.buildFrom(EPermission.EXPERTISE_DELETE),
                        Permission.buildFrom(EPermission.EXPERTISE_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.EXPERTISE_VIEW_LIST),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_CREATE),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_UPDATE),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_DELETE),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW_LIST));
            }
            case BRANCH_MANAGER -> {
                return List.of(
                        Permission.buildFrom(EPermission.ACCOUNT_CREATE),
                        Permission.buildFrom(EPermission.ACCOUNT_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_DELETE),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_LIST),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_VIEW),
                        Permission.buildFrom(EPermission.EXPERTISE_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW_LIST));
            }
            case SERVICE_STAFF -> {
                return List.of(
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_LIST),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW_LIST));
            }
            case RECEPTIONIST -> {
                return List.of(
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_LIST),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW_LIST));
            }
            case CUSTOMER -> {
                return List.of(
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_UPDATE),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_LIST),
                        Permission.buildFrom(EPermission.ACCOUNT_VIEW_DETAIL),
                        Permission.buildFrom(EPermission.ACCOUNT_SELF_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW),
                        Permission.buildFrom(EPermission.BARBER_CATEGORY_VIEW_LIST));
            }
            default -> {
                return List.of();
            }
        }
    }
    /*1-end*/

    /*2-Khởi tạo tài khoản*/
    private void configAccount() {
        if(!accountUseCase.existAccountByPhone(AccountFindByPhoneAction.buildFrom(initAccountPhone))) {
            Account account = Account.builder()
                    .firstName(initAccountFirstName)
                    .lastName(initAccountLastName)
                    .password(defaultPassword)
                    .phone(initAccountPhone)
                    .build();
            accountUseCase.save(AccountCreateAction.buildFrom(account, ERole.SHOP_OWNER));
        }
    }
    /*2-end*/
}
