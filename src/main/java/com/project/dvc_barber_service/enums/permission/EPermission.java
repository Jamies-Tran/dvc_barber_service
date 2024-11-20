package com.project.dvc_barber_service.enums.permission;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EPermission {
    ACCOUNT_CREATE("ACCOUNT_CREATE", "account", "create"),
    ACCOUNT_UPDATE("ACCOUNT_UPDATE", "account", "update"),
    ACCOUNT_SELF_UPDATE("ACCOUNT_SELF_UPDATE", "account", "self-update"),
    ACCOUNT_VIEW_LIST("ACCOUNT_VIEW_LIST", "account", "view-list"),
    ACCOUNT_VIEW_DETAIL("ACCOUNT_VIEW_DETAIL", "account", "view-detail"),
    ACCOUNT_SELF_VIEW("ACCOUNT_SELF_VIEW", "account", "self-view"),
    ACCOUNT_DELETE("ACCOUNT_DELETE", "account", "delete"),
    BRANCH_CREATE("BRANCH_CREATE", "branch", "create"),
    EXPERTISE_CREATE("EXPERTISE_CREATE", "expertise", "create"),
    EXPERTISE_UPDATE("EXPERTISE_UPDATE", "expertise", "update"),
    EXPERTISE_DELETE("EXPERTISE_DELETE", "expertise", "delete"),
    EXPERTISE_VIEW_DETAIL("EXPERTISE_VIEW_DETAIL", "expertise", "view-detail"),
    EXPERTISE_VIEW_LIST("EXPERTISE_VIEW_LIST", "expertise", "view-list"),
    BARBER_CATEGORY_CREATE("BARBER_CATEGORY_CREATE", "barber-category", "create"),
    BARBER_CATEGORY_UPDATE("BARBER_CATEGORY_UPDATE", "barber-category", "update"),
    BARBER_CATEGORY_DELETE("BARBER_CATEGORY_DELETE", "barber-category", "delete"),
    BARBER_CATEGORY_VIEW("BARBER_CATEGORY_VIEW", "barber-category", "view"),
    BARBER_CATEGORY_VIEW_LIST("BARBER_CATEGORY_VIEW_LIST", "barber-category", "view-list");

    String code;
    String key;
    String name;

    public String authority() {
        return "%s:%s".formatted(this.getKey(), this.getName());
    }

    public static List<EPermission> getList() {
        return Stream.of(values()).toList();
    }

}
