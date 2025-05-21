package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.entity.BoardLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeJpaRepository extends JpaRepository<BoardLikeEntity, Long> {
    boolean existsByUserIdAndBoardId(Long userId, Long boardId);

    void deleteAllByUserIdAndBoardId(Long userId, Long boardId);

    long countByBoardId(Long boardId);
}
