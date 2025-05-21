package com.nts.ntsboard.controller.response;

public record BoardTotalCountResponse(
        long totalBoardCount
) {
    public static BoardTotalCountResponse from(long totalBoardCount) {
        return new BoardTotalCountResponse(
                totalBoardCount
        );
    }
}
