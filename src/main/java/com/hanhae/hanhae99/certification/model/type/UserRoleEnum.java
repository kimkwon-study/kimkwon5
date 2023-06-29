package com.hanhae.hanhae99.certification.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

    USER("user"),
    ADMIN("admin")
    ;

    private final String role;

}
