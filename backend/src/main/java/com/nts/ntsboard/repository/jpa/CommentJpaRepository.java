package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.dto.BoardIdCountPair;
import com.nts.ntsboard.repository.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
    Slice<CommentEntity> findByBoardId(Long boardId, Pageable pageable);
    @Query("SELECT c.boardId as boardId, COALESCE(COUNT(c.id), 0) as count " +
            "FROM CommentEntity c " +
            "WHERE c.boardId IN :boardIds " +
            "GROUP BY c.boardId")
    List<BoardIdCountPair> countCommentsByBoardIds(List<Long> boardIds);
}
