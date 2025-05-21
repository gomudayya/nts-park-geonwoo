package com.nts.ntsboard.exception;

import com.nts.ntsboard.common.exception.BusinessException;
import com.nts.ntsboard.common.exception.ErrorCode;

public class AlreadyLikeException extends BusinessException {
    public AlreadyLikeException() {
        super(ErrorCode.ALREADY_LIKE);
    }
}
