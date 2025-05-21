package com.nts.ntsboard.common.exception;

import com.nts.ntsboard.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<?>> handleBindException(BindException bindException) {
        return ResponseEntity.status(400)
                .body(ApiResponse.fail(ErrorCode.INVALID_INPUT_VALUE));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException businessException) {
        ErrorCode errorCode = businessException.getErrorCode();
        ErrorPayload errorPayload = new ErrorPayload(
                errorCode.name(),
                businessException.getMessage()
        );

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.fail(errorPayload, errorCode.getStatusCode()));
    }
}
