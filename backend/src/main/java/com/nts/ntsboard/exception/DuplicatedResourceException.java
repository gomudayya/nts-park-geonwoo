package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.exception.BusinessException;
import com.nts.ntsboard.common.exception.ErrorCode;

public class DuplicatedResourceException extends BusinessException {
    public DuplicatedResourceException(String resource) {
        super(ErrorCode.DUPLICATED_RESOURCE, String.format(ErrorCode.DUPLICATED_RESOURCE.getMessage(), resource));
    }
}
