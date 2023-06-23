package com.hanhae.hanhae99.global.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Password
    NO_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 맞지 않습니다."),

    ;

    private final HttpStatus status;
    private final String message;
}
