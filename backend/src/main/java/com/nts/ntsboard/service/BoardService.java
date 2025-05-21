package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.BoardWriteRequest;
import com.nts.ntsboard.controller.response.BoardDetailResponse;
import com.nts.ntsboard.controller.response.CountResponse;
import com.nts.ntsboard.domain.Board;
import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.exception.AccessDeniedException;
import com.nts.ntsboard.repository.BoardRepository;
import com.nts.ntsboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final HashtagService hashtagService;

    @Transactional
    public BoardDetailResponse createBoard(Long writerId, BoardWriteRequest request) {
        User writer = userRepository.findById(writerId);
        List<Hashtag> hashtags = hashtagService.findOrCreateHashtags(request.hashtags());

        Board board = Board.createBoard(writer, request.title(), request.content(), hashtags);
        board = boardRepository.save(board);
        return BoardDetailResponse.from(board);
    }

    @Transactional
    public BoardDetailResponse updateBoard(Long userId, Long boardId, BoardWriteRequest request) {
        Board board = boardRepository.findById(boardId);
        if (!board.isCreatedBy(userId)) {
            throw new AccessDeniedException();
        }

        boardRepository.deleteAllHashtags(boardId);
        List<Hashtag> hashtags = hashtagService.findOrCreateHashtags(request.hashtags());
        board.update(request.title(), request.content(), hashtags);

        boardRepository.save(board);
        return BoardDetailResponse.from(board);
    }

    @Transactional
    public void deleteBoard(Long userId, Long boardId) {
        Board board = boardRepository.findById(boardId);
        if (!board.isCreatedBy(userId)) {
            throw new AccessDeniedException();
        }

        boardRepository.deleteById(boardId);
    }

    @Transactional(readOnly = true)
    public BoardDetailResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId);
        board.increaseViewCount();
        return BoardDetailResponse.from(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoardByPage(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        return boardRepository.findPage(pageable);
    }

    @Transactional(readOnly = true)
    public CountResponse getBoardCount() {
        long count = boardRepository.count();
        return CountResponse.from(count);
    }
}
