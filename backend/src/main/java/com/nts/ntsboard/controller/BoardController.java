package com.nts.ntsboard.controller;

import com.nts.ntsboard.common.ApiResponse;
import com.nts.ntsboard.common.auth.AuthPrincipal;
import com.nts.ntsboard.controller.request.BoardCreateRequest;
import com.nts.ntsboard.controller.response.BoardDetailResponse;
import com.nts.ntsboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ApiResponse<BoardDetailResponse> createBoard(@RequestBody BoardCreateRequest request,
                                                        @AuthPrincipal Long userId) {
        return ApiResponse.success(boardService.createBoard(userId, request), 201);
    }
}
