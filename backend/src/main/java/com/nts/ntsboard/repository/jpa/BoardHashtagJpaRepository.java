package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.entity.BoardHashtagEntity;
import com.nts.ntsboard.repository.entity.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardHashtagJpaRepository extends JpaRepository<BoardHashtagEntity, Long> {
    @Query("""
                SELECT h
                FROM BoardHashtagEntity bh
                JOIN bh.hashtagEntity h
                WHERE bh.boardEntity.id = :boardId
            """)
    List<HashtagEntity> findAllHashtagByBoardId(@Param("boardId") Long boardId);

    @Modifying
    @Query("DELETE FROM BoardHashtagEntity bh WHERE bh.boardEntity.id = :boardId")
    void deleteAllByBoardId(Long boardId);
}
