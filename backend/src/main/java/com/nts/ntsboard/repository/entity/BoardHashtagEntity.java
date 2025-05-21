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
        name = "board_hashtag",
        indexes = {
                @Index(name = "idx_board_id", columnList = "boardId"),
                @Index(name = "idx_hashtag_id", columnList = "hashtagId")
        }
)
public class BoardHashtagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    private Long hashtagId;

    public static BoardHashtagEntity link(Long boardId, Long hashtagId) {
        BoardHashtagEntity link = new BoardHashtagEntity();
        link.boardId = boardId;
        link.hashtagId = hashtagId;
        return link;
    }
}
