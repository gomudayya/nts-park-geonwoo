package com.nts.ntsboard.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Comment {
    private Long commentId;
    private User writer;
    private Long boardId;
    private String content;
    private LocalDateTime createdTime;

    @Builder
    public Comment(Long commentId, User writer, Long boardId, String content, LocalDateTime createdTime) {
        this.commentId = commentId;
        this.writer = writer;
        this.boardId = boardId;
        this.content = content;
        this.createdTime = createdTime;
    }

    public static Comment createComment(User writer, Long boardId, String content) {
        return Comment.builder()
                .writer(writer)
                .boardId(boardId)
                .content(content)
                .createdTime((LocalDateTime.now()))
                .build();
    }

    public Long getWriterId() {
        return writer.getId();
    }
    public String getWriterNickname() {
        return writer.getNickname();
    }
}
