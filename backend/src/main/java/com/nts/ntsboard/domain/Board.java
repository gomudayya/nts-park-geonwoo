package com.nts.ntsboard.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Board {
    private Long boardId;
    private User writer;
    private String title;
    private String content;
    private int viewCount;
    private List<Hashtag> hashtags;
    private LocalDateTime createdTime;

    protected Board() {
    }

    @Builder
    public Board(Long boardId, User writer, String title, String content, int viewCount, List<Hashtag> hashtags, LocalDateTime createdTime) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.hashtags = hashtags;
        this.createdTime = createdTime;
    }

    public static Board createBoard(User writer, String title, String content, List<Hashtag> hashtags) {
        Board board = new Board();
        board.writer = writer;
        board.title = title;
        board.content = content;
        board.hashtags = hashtags;
        board.createdTime = LocalDateTime.now();
        return board;
    }

    public List<String> getHashtagNames() {
        return hashtags.stream().map(Hashtag::getTagName).toList();
    }

    public boolean isCreatedBy(Long userId) {
        return this.getWriter().getId().equals(userId);
    }

    public void update(String title, String content, List<Hashtag> hashtags) {
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
    }

    public void increaseViewCount() {
        viewCount++;
    }

    public String getWriterNickname() {
        return writer.getNickname();
    }
}
