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
    private boolean isDeleted;

    @Builder
    public Comment(Long commentId, User writer, Long boardId, String content, LocalDateTime createdTime, boolean isDeleted) {
        this.commentId = commentId;
        this.writer = writer;
        this.boardId = boardId;
        this.content = content;
        this.createdTime = createdTime;
        this.isDeleted = isDeleted;
    }

    public static Comment createComment(User writer, Long boardId, String content) {
        return Comment.builder()
                .writer(writer)
                .boardId(boardId)
                .content(content)
                .createdTime((LocalDateTime.now()))
                .isDeleted(false)
                .build();
    }

    public Long getWriterId() {
        return writer.getId();
    }

    public String getWriterNickname() {
        return writer.getNickname();
    }

    public boolean isCreatedBy(Long userId) {
        return getWriterId().equals(userId);
    }

    public void update(String content) {
        this.content = content;
    }

    public void delete() {
        isDeleted = true;
    }
}
