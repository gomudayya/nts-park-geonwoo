package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long commentId,
        Long boardId,
        Long writerId,
        String writerNickname,
        String content,
        LocalDateTime createdTime
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getBoardId(),
                comment.getWriterId(),
                comment.getWriterNickname(),
                comment.isDeleted() ? "삭제된 댓글입니다." : comment.getContent(),
                comment.getCreatedTime()
        );
    }
}
