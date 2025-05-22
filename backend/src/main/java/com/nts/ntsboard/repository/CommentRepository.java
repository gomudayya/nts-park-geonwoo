package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.Comment;
import com.nts.ntsboard.exception.NotFoundException;
import com.nts.ntsboard.repository.dto.BoardIdCountPair;
import com.nts.ntsboard.repository.entity.CommentEntity;
import com.nts.ntsboard.repository.jpa.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final CommentJpaRepository commentJpaRepository;
    public Comment save(Comment comment) {
        return commentJpaRepository.save(CommentEntity.from(comment)).toModel();
    }

    public Comment findById(Long commentId) {
        return commentJpaRepository.findById(commentId).orElseThrow(() -> new NotFoundException("댓글")).toModel();
    }

    public Slice<Comment> findSliceByBoardId(Long boardId, Pageable pageable) {
        Slice<CommentEntity> commentEntities = commentJpaRepository.findByBoardId(boardId, pageable);
        return commentEntities.map(CommentEntity::toModel);
    }

    public long count() {
        return commentJpaRepository.count();
    }

    public List<BoardIdCountPair> countCommentsByBoardIds(List<Long> boardIds) {
        return commentJpaRepository.countCommentsByBoardIds(boardIds);
    }
}
