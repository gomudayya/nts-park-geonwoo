package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.Board;

import java.time.LocalDateTime;
import java.util.List;

public record BoardDetailResponse(
        Long boardId,
        String title,
        String content,
        List<String> hashtags,
        LocalDateTime createdTime
) {
    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getHashtagNames(),
                board.getCreatedTime()
        );
    }
}

