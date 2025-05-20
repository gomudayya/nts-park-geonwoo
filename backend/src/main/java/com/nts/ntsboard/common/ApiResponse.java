package com.nts.ntsboard.common;

public record ApiResponse<T>(
        boolean success,
        int statusCode,
        T data,
        ErrorPayload error
) {
    public static <T> ApiResponse<T> success(T data, int statusCode) {
        return new ApiResponse<>(true, statusCode, data, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        ErrorPayload errorPayload = new ErrorPayload(errorCode.name(), errorCode.getMessage());
        return new ApiResponse<>(false, errorCode.getStatusCode(), null, errorPayload);
    }

    public static <T> ApiResponse<T> fail(ErrorPayload errorPayload, int statusCode) {
        return new ApiResponse<>(false, statusCode, null, errorPayload);
    }
}
