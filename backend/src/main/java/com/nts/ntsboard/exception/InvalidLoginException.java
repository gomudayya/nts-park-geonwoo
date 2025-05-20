package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.exception.BusinessException;
import com.nts.ntsboard.common.exception.ErrorCode;

public class InvalidLoginException extends BusinessException {
    public InvalidLoginException() {
        super(ErrorCode.INVALID_LOGIN);
    }
}
