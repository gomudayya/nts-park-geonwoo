package com.nts.ntsboard.controller.response;

public record LikeStatusResponse(
        boolean liked
) {
    public static LikeStatusResponse from(boolean liked) {
        return new LikeStatusResponse(
                liked
        );
    }
}
