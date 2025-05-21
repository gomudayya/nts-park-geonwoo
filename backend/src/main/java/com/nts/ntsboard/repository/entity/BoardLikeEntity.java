package com.nts.ntsboard.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(
        name = "likes",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_post_id", columnList = "board_id")
        }
)
public class BoardLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long boardId;

    public static BoardLikeEntity from(Long userId, Long boardId) {
        BoardLikeEntity boardLikeEntity = new BoardLikeEntity();
        boardLikeEntity.userId = userId;
        boardLikeEntity.boardId = boardId;
        return boardLikeEntity;
    }
}
