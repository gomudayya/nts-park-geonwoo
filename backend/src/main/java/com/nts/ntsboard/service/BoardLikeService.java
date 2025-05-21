package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.response.CountResponse;
import com.nts.ntsboard.controller.response.LikeStatusResponse;
import com.nts.ntsboard.exception.AlreadyLikeException;
import com.nts.ntsboard.repository.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public void like(Long userId, Long boardId) {
        if (boardLikeRepository.existsByUserIdAndBoardId(userId, boardId)) {
            throw new AlreadyLikeException();
        }

        boardLikeRepository.save(userId, boardId);
    }

    @Transactional
    public void unlike(Long userId, Long boardId) {
        //중복 저장된 경우를 대비해 DeleteAll 으로 삭제
        boardLikeRepository.deleteAllByUserIdAndBoardId(userId, boardId);
    }

    @Transactional(readOnly = true)
    public CountResponse getLikeCount(Long boardId) {
        long likeCount = boardLikeRepository.countByBoardId(boardId);
        return CountResponse.from(likeCount);
    }

    @Transactional(readOnly = true)
    public LikeStatusResponse getLikeStatus(Long userId, Long boardId) {
        boolean liked = boardLikeRepository.existsByUserIdAndBoardId(userId, boardId);
        return LikeStatusResponse.from(liked);
    }
}
