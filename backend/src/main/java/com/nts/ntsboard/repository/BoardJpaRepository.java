package com.nts.ntsboard.repository;

import com.nts.ntsboard.repository.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
}
