package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.BusinessException;
import com.nts.ntsboard.common.ErrorCode;

public class InvalidLoginException extends BusinessException {
    public InvalidLoginException() {
        super(ErrorCode.INVALID_LOGIN);
    }
}
