package com.nts.ntsboard.service;

import com.nts.ntsboard.controller.request.BoardCreateRequest;
import com.nts.ntsboard.controller.response.BoardDetailResponse;
import com.nts.ntsboard.domain.Board;
import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.domain.User;
import com.nts.ntsboard.repository.BoardRepository;
import com.nts.ntsboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public BoardDetailResponse createBoard(Long writerId, BoardCreateRequest request) {
        User writer = userRepository.findById(writerId);
        List<Hashtag> hashtags = hashtagService.syncHashtags(request.hashtags());

        Board board = Board.createBoard(writer, request.content(), hashtags);
        board = boardRepository.save(board);
        return BoardDetailResponse.from(board);
    }
}
