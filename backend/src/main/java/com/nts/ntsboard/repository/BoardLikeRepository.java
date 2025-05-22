package com.nts.ntsboard.repository;

import com.nts.ntsboard.repository.dto.BoardIdCountPair;
import com.nts.ntsboard.repository.entity.BoardLikeEntity;
import com.nts.ntsboard.repository.jpa.BoardLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardLikeRepository {
    private final BoardLikeJpaRepository boardLikeJpaRepository;

    public boolean existsByUserIdAndBoardId(Long userId, Long boardId) {
        return boardLikeJpaRepository.existsByUserIdAndBoardId(userId, boardId);
    }

    public void save(Long userId, Long boardId) {
        BoardLikeEntity boardLikeEntity = BoardLikeEntity.from(userId, boardId);
        boardLikeJpaRepository.save(boardLikeEntity);
    }

    public void deleteAllByUserIdAndBoardId(Long userId, Long boardId) {
        boardLikeJpaRepository.deleteAllByUserIdAndBoardId(userId, boardId);
    }

    public long countByBoardId(Long boardId) {
        return boardLikeJpaRepository.countByBoardId(boardId);
    }

    public List<BoardIdCountPair> countLikesByBoardIds(List<Long> boardIds) {
        return boardLikeJpaRepository.countLikesByBoardIds(boardIds);
    }
}
