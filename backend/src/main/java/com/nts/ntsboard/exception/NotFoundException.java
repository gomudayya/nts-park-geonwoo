package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.exception.BusinessException;
import com.nts.ntsboard.common.exception.ErrorCode;

public class NotFoundException extends BusinessException {
    public NotFoundException(String resource) {
        super(ErrorCode.NOT_FOUND, String.format(ErrorCode.NOT_FOUND.getMessage(), resource));
    }
}
