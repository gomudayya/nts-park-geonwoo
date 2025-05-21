package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.exception.BusinessException;
import com.nts.ntsboard.common.exception.ErrorCode;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}
