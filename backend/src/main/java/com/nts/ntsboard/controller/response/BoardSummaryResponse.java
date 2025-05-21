package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.Board;

import java.time.LocalDateTime;

public record BoardSummaryResponse(
        Long boardId,
        Long writerId,
        String title,
        String writerNickname,
        LocalDateTime createdTime,
        long viewCount,
        long commentCount,
        long likeCount
) {
    public static BoardSummaryResponse from(Board board, long commentCount, long likeCount) {
        return new BoardSummaryResponse(
                board.getBoardId(),
                board.getWriterId(),
                board.getTitle(),
                board.getWriterNickname(),
                board.getCreatedTime(),
                board.getViewCount(),
                commentCount,
                likeCount
        );
    }
}
