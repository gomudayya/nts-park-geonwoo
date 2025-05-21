package com.nts.ntsboard.repository.entity;

import com.nts.ntsboard.domain.Board;
import com.nts.ntsboard.domain.Hashtag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;
    private String title;
    private String content;
    private int viewCount;

    public static BoardEntity from(Board board) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.id = board.getBoardId();
        boardEntity.writer = UserEntity.from(board.getWriter());
        boardEntity.title = board.getTitle();
        boardEntity.content = board.getContent();
        boardEntity.viewCount = board.getViewCount();
        return boardEntity;
    }

    public Board toModel(List<Hashtag> hashtags) {
        return Board.builder()
                .boardId(id)
                .writer(writer.toModel())
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .hashtags(hashtags)
                .build();
    }
}
