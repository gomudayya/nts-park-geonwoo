package com.nts.ntsboard.service;

import com.nts.ntsboard.common.response.SliceResponse;
import com.nts.ntsboard.controller.request.CommentWriteRequest;
import com.nts.ntsboard.controller.response.CommentResponse;
import com.nts.ntsboard.controller.response.CountResponse;
import com.nts.ntsboard.domain.Comment;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.exception.AccessDeniedException;
import com.nts.ntsboard.repository.CommentRepository;
import com.nts.ntsboard.repository.UserRepository;
import com.nts.ntsboard.repository.dto.BoardIdCountPair;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse createComment(Long writerId, Long boardId, CommentWriteRequest request) {
        User writer = userRepository.findById(writerId);
        Comment comment = Comment.createComment(writer, boardId, request.content());

        comment = commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Transactional
    public CommentResponse updateComment(Long userId, Long commentId, CommentWriteRequest request) {
        Comment comment = commentRepository.findById(commentId);
        if (!comment.isCreatedBy(userId)) {
            throw new AccessDeniedException();
        }

        comment.update(request.content());
        comment = commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Transactional
    public CommentResponse deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        if (!comment.isCreatedBy(userId)) {
            throw new AccessDeniedException();
        }

        comment.delete();
        comment = commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Transactional(readOnly = true)
    public SliceResponse<CommentResponse> getCommentBySlice(Long boardId, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Slice<Comment> commentSlice = commentRepository.findSliceByBoardId(boardId, pageable);
        Slice<CommentResponse> responseSlice = commentSlice.map(CommentResponse::from);
        return SliceResponse.from(responseSlice);
    }

    @Transactional(readOnly = true)
    public CountResponse getCommentCount() {
        long count = commentRepository.count();
        return CountResponse.from(count);
    }

    public Map<Long, Long> getBoardCommentCountMap(List<Long> boardIds) {
        List<BoardIdCountPair> dtos = commentRepository.countCommentsByBoardIds(boardIds);
        return dtos.stream()
                .collect(Collectors.toMap(BoardIdCountPair::getBoardId, BoardIdCountPair::getCount));
    }
}
