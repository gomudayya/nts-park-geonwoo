package com.nts.ntsboard.repository.entity;

import com.nts.ntsboard.domain.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;
    private Long boardId;
    private String content;
    private LocalDateTime createdTime;
    private boolean isDeleted;

    public static CommentEntity from(Comment comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.id = comment.getCommentId();
        commentEntity.writer = UserEntity.from(comment.getWriter());
        commentEntity.boardId = comment.getBoardId();
        commentEntity.content = comment.getContent();
        commentEntity.createdTime = comment.getCreatedTime();
        commentEntity.isDeleted = comment.isDeleted();
        return commentEntity;
    }

    public Comment toModel() {
        return Comment.builder()
                .commentId(id)
                .writer(writer.toModel())
                .boardId(boardId)
                .content(content)
                .createdTime(createdTime)
                .isDeleted(isDeleted)
                .build();
    }
}
