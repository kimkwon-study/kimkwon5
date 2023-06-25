package com.hanhae.hanhae99.certification.model.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRoleEnum {

    USER("user"),
    ADMIN("admin")
    ;

    private final String role;

}
