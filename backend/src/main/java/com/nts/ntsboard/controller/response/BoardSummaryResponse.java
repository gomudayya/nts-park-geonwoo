package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.Board;

import java.time.LocalDateTime;

public record BoardSummaryResponse(
        Long boardId,
        String title,
        String writer,
        LocalDateTime createdTime,
        Integer commentCount,
        Integer viewCount,
        Integer likeCount
) {
    public static BoardSummaryResponse from(Board board, Integer commentCount, Integer viewCount, Integer likeCount) {
        return new BoardSummaryResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getWriterNickname(),
                board.getCreatedTime(),
                commentCount,
                viewCount,
                likeCount
        );
    }
}
