package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.dto.BoardIdCountPair;
import com.nts.ntsboard.repository.entity.BoardLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardLikeJpaRepository extends JpaRepository<BoardLikeEntity, Long> {
    boolean existsByUserIdAndBoardId(Long userId, Long boardId);

    void deleteAllByUserIdAndBoardId(Long userId, Long boardId);

    long countByBoardId(Long boardId);

    @Query("SELECT b.boardId AS boardId, COUNT(b.id) AS count " +
            "FROM BoardLikeEntity b " +
            "WHERE b.boardId IN :boardIds " +
            "GROUP BY b.boardId")
    List<BoardIdCountPair> countLikesByBoardIds(@Param("boardIds") List<Long> boardIds);
}
