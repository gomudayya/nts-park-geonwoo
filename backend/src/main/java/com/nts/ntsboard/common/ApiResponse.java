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

    public static <T> ApiResponse<T> failure(ErrorPayload error, int statusCode) {
        return new ApiResponse<>(false, statusCode, null, error);
    }
}
