package com.nts.ntsboard.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "board_hashtag")
public class BoardHashtagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne()
    @JoinColumn(name = "hashtag_id")
    private HashtagEntity hashtagEntity;

    public static BoardHashtagEntity link(BoardEntity boardEntity, HashtagEntity hashtagEntity) {
        BoardHashtagEntity link = new BoardHashtagEntity();
        link.boardEntity = boardEntity;
        link.hashtagEntity = hashtagEntity;
        return link;
    }
}
