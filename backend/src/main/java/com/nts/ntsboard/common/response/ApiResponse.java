package com.nts.ntsboard.common.response;

import com.nts.ntsboard.common.exception.ErrorCode;
import com.nts.ntsboard.common.exception.ErrorPayload;

public record ApiResponse<T>(
        boolean isSuccess,
        int statusCode,
        T content,
        ErrorPayload error
) {
    public static <T> ApiResponse<T> success(T content, int statusCode) {
        return new ApiResponse<>(true, statusCode, content, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode, String errorMessage) {
        ErrorPayload errorPayload = new ErrorPayload(errorCode.name(), errorMessage);
        return new ApiResponse<>(false, errorCode.getStatusCode(), null, errorPayload);
    }
}
