package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.Board;
import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.exception.NotFoundException;
import com.nts.ntsboard.repository.entity.BoardEntity;
import com.nts.ntsboard.repository.entity.BoardHashtagEntity;
import com.nts.ntsboard.repository.entity.HashtagEntity;
import com.nts.ntsboard.repository.jpa.BoardHashtagJpaRepository;
import com.nts.ntsboard.repository.jpa.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardHashtagJpaRepository boardHashtagJpaRepository;

    public Board save(Board board) {
        //게시글 저장
        BoardEntity boardEntity = boardJpaRepository.save(BoardEntity.from(board));

        //게시글과 해시태그 연결 (연관테이블)
        List<Hashtag> hashtags = board.getHashtags();
        List<BoardHashtagEntity> links = hashtags.stream()
                .map(hashtag -> BoardHashtagEntity.link(boardEntity, HashtagEntity.from(hashtag)))
                .toList();

        boardHashtagJpaRepository.saveAll(links);
        return boardEntity.toModel(hashtags);
    }

    public Board findById(Long boardId) {
        List<Hashtag> hashtags = boardHashtagJpaRepository.findAllHashtagByBoardId(boardId).stream()
                .map(HashtagEntity::toModel)
                .toList();
        return boardJpaRepository.findById(boardId).orElseThrow(() -> new NotFoundException("게시글")).toModel(hashtags);
    }

    public void deleteById(Long boardId) {
        deleteAllHashtags(boardId);
        boardJpaRepository.deleteById(boardId);
    }
    public void deleteAllHashtags(Long boardId) {
        boardHashtagJpaRepository.deleteAllByBoardId(boardId);
    }
}
