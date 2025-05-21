package com.nts.ntsboard.repository;

import com.nts.ntsboard.repository.entity.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagJpaRepository extends JpaRepository<HashtagEntity, Long> {
    List<HashtagEntity> findAllByTagNameIn(List<String> tagNames);
}
