package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.Board;
import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.exception.NotFoundException;
import com.nts.ntsboard.repository.entity.BoardEntity;
import com.nts.ntsboard.repository.entity.BoardHashtagEntity;
import com.nts.ntsboard.repository.jpa.BoardHashtagJpaRepository;
import com.nts.ntsboard.repository.jpa.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardHashtagJpaRepository boardHashtagJpaRepository;
    private final HashtagRepository hashtagRepository;

    public Board save(Board board) {
        //게시글 저장
        BoardEntity boardEntity = boardJpaRepository.save(BoardEntity.from(board));

        //게시글과 해시태그 연결 (연관테이블)
        List<Hashtag> hashtags = board.getHashtags();
        List<BoardHashtagEntity> links = hashtags.stream()
                .map(hashtag -> BoardHashtagEntity.link(boardEntity.getId(), hashtag.getId()))
                .toList();

        boardHashtagJpaRepository.saveAll(links);
        return boardEntity.toModel(hashtags);
    }

    public Board findById(Long boardId) {
        List<Long> hashtagIds = boardHashtagJpaRepository.findAllByBoardId(boardId).stream()
                .map(BoardHashtagEntity::getHashtagId)
                .toList();

        List<Hashtag> hashtags = hashtagRepository.findAllById(hashtagIds);
        return boardJpaRepository.findById(boardId).orElseThrow(() -> new NotFoundException("게시글")).toModel(hashtags);
    }

    public void deleteById(Long boardId) {
        deleteAllHashtags(boardId);
        boardJpaRepository.deleteById(boardId);
    }

    public void deleteAllHashtags(Long boardId) {
        boardHashtagJpaRepository.deleteAllByBoardId(boardId);
    }

    public Page<Board> findPageWithNoHashtag(Pageable pageable) {
        Page<BoardEntity> boardEntities = boardJpaRepository.findAll(pageable);
        return boardEntities.map(boardEntity -> boardEntity.toModel(Collections.emptyList()));
    }

    public Page<Board> findPageWithHashtags(Pageable pageable) {
        Page<BoardEntity> boardEntities = boardJpaRepository.findAll(pageable);
        List<Long> boardIds = boardEntities.map(BoardEntity::getId).toList();
        List<BoardHashtagEntity> boardHashtagEntities = boardHashtagJpaRepository.findByBoardIdIn(boardIds);

        Map<Long, List<Long>> map = new HashMap<>(); // <boardId, hashtagIds> // 게시글에 대응되는 해시태그 Ids 조회
        Set<Long> hashtagIds = new HashSet<>();
        for (BoardHashtagEntity relation : boardHashtagEntities) {
            map.putIfAbsent(relation.getBoardId(), new ArrayList<>());
            map.get(relation.getBoardId()).add(relation.getHashtagId());
            hashtagIds.add(relation.getHashtagId());
        }

        hashtagRepository.findAllById(new ArrayList<>(hashtagIds)); // 영속성 컨텍스트 로드
        return boardEntities.map(boardEntity -> {
            List<Hashtag> linkedHashtags = findByHashtagIds(map.get(boardEntity.getId())); // 영속성 컨텍스트에서 가져옴
            return boardEntity.toModel(linkedHashtags);
        });
    }

    private List<Hashtag> findByHashtagIds(List<Long> hashtagIds) {
        if (hashtagIds == null) return Collections.emptyList();
        return hashtagIds.stream()
                .map(hashtagRepository::findById)
                .toList();
    }

    public long count() {
        return boardJpaRepository.count();
    }
}
