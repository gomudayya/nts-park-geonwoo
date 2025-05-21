package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.Board;

import java.time.LocalDateTime;
import java.util.List;

public record BoardDetailResponse(
        Long boardId,
        Long writerId,
        String writerNickname,
        String title,
        String content,
        List<String> hashtags,
        LocalDateTime createdTime
) {
    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(
                board.getBoardId(),
                board.getWriterId(),
                board.getWriterNickname(),
                board.getTitle(),
                board.getContent(),
                board.getHashtagNames(),
                board.getCreatedTime()
        );
    }
}

