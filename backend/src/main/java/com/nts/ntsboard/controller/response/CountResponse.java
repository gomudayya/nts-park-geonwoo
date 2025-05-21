package com.nts.ntsboard.controller.response;

public record CountResponse(
        long count
) {
    public static CountResponse from(long count) {
        return new CountResponse(count);
    }
}
