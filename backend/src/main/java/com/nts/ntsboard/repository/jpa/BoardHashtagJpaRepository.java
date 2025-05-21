package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.entity.BoardHashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardHashtagJpaRepository extends JpaRepository<BoardHashtagEntity, Long> {

    List<BoardHashtagEntity> findByBoardIdIn(List<Long> boardIds);

    void deleteAllByBoardId(Long boardId);

    List<BoardHashtagEntity> findAllByBoardId(Long boardId);
}
