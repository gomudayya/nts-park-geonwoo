package com.nts.ntsboard.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    
    //범용에러
    INVALID_INPUT_VALUE(400, "유효하지 않은 입력입니다."),
    INVALID_AUTHENTICATION(401, "올바르지 않은 인증 정보입니다."),
    ACCESS_DENIED(403, "권한이 부족합니다."),
    NOT_FOUND(404, "해당 %s를 찾을 수 없습니다."),
    
    //비즈니스 에러
    ALREADY_LIKE(400, "이미 좋아요한 게시글입니다.")
    ;
    private final int statusCode;
    private final String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
