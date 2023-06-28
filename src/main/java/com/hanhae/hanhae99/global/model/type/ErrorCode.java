package com.hanhae.hanhae99.global.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Password
    NO_PASSWORD(HttpStatus.UNAUTHORIZED, "사용자가 작성한 게시글이 아닙니다."),
    NO_PID(HttpStatus.BAD_REQUEST, "잘못된 게시글 정보입니다."),

    //Login / Register
    LOGIN_NO(HttpStatus.UNAUTHORIZED, "아이디나 비밀번호가 맞지 않습니다."),
    NAME_SAME(HttpStatus.UNAUTHORIZED, "중복되는 닉네임 입니다."),

    //Token
    WRONG_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰값 입니다."),

    //댓글
    WRONG_BOARD_PID(HttpStatus.NOT_FOUND, "잘못된 게시글 접근 입니다."),
    WRONG_COMMENT_PID(HttpStatus.NOT_FOUND, "잘못된 댓글 접근 입니다."),
    WRONG_NAME(HttpStatus.NOT_FOUND, "사용자가 작성한 댓글이 아닙니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
