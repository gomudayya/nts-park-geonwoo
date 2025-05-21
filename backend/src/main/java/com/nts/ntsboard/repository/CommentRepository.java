package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.Comment;
import com.nts.ntsboard.repository.entity.CommentEntity;
import com.nts.ntsboard.repository.jpa.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final CommentJpaRepository commentJpaRepository;
    public Comment save(Comment comment) {
        return commentJpaRepository.save(CommentEntity.from(comment)).toModel();
    }
}
