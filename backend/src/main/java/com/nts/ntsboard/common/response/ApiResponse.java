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

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        ErrorPayload errorPayload = new ErrorPayload(errorCode.name(), errorCode.getMessage());
        return new ApiResponse<>(false, errorCode.getStatusCode(), null, errorPayload);
    }

    public static <T> ApiResponse<T> fail(ErrorPayload errorPayload, int statusCode) {
        return new ApiResponse<>(false, statusCode, null, errorPayload);
    }
}
