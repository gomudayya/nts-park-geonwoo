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
        name = "board_like",
        indexes = {
                // 복합인덱스 컬럼순서 중요함. board_id 조건으로 게시글 좋아요 갯수 세야하니 board_Id가 먼저와야함.
                @Index(name = "idx_board_user", columnList = "board_id, user_id"),
                @Index(name = "idx_user_id", columnList = "user_id")
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
