package com.nts.ntsboard.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Board {
    private Long boardId;
    private User writer;
    private String content;
    private int viewCount;
    private List<Hashtag> hashtags;

    protected Board() {}

    @Builder
    public Board(Long boardId, User writer, String content, int viewCount, List<Hashtag> hashtags) {
        this.boardId = boardId;
        this.writer = writer;
        this.content = content;
        this.viewCount = viewCount;
        this.hashtags = hashtags;
    }

    public static Board createBoard(User writer, String content, List<Hashtag> hashtags) {
        Board board = new Board();
        board.writer = writer;
        board.content = content;
        board.hashtags = hashtags;
        return board;
    }

    public List<String> getHashtagNames() {
        return hashtags.stream().map(Hashtag::getTagName).toList();
    }
}
