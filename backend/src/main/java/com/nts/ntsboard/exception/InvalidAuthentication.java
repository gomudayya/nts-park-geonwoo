package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.exception.BusinessException;
import com.nts.ntsboard.common.exception.ErrorCode;

public class InvalidAuthentication extends BusinessException {
    public InvalidAuthentication() {
        super(ErrorCode.INVALID_AUTHENTICATION);
    }
}
