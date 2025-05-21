package com.nts.ntsboard.repository.jpa;

import com.nts.ntsboard.repository.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
}
