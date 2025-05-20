package com.nts.ntsboard.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "유효하지 않은 입력입니다."),
    NOT_FOUND(404, "해당 %s를 찾을 수 없습니다."),
    INVALID_LOGIN(401, "올바르지 않은 로그인 정보입니다.")
    ;
    private final int statusCode;
    private final String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
