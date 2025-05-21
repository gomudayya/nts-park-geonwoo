package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.Board;
import com.nts.ntsboard.domain.Hashtag;

import java.util.List;

public record BoardDetailResponse(
        Long boardId,
        String title,
        String content,
        List<String> hashtags
) {
    public static BoardDetailResponse from(Board board) {
        List<String> hashtags = board.getHashtags().stream().map(Hashtag::getTagName).toList();
        return new BoardDetailResponse(
                board.getBoardId(),
                board.getContent(),
                board.getTitle(),
                hashtags
        );
    }
}

