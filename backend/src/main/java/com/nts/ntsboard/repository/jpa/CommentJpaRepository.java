package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
    Slice<CommentEntity> findByBoardId(Long boardId, Pageable pageable);

}
