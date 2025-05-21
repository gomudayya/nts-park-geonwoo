package com.nts.ntsboard.controller;

import com.nts.ntsboard.common.ApiResponse;
import com.nts.ntsboard.common.auth.AuthPrincipal;
import com.nts.ntsboard.controller.request.BoardWriteRequest;
import com.nts.ntsboard.controller.response.BoardDetailResponse;
import com.nts.ntsboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ApiResponse<BoardDetailResponse> createBoard(@RequestBody BoardWriteRequest request,
                                                        @AuthPrincipal Long userId) {
        return ApiResponse.success(boardService.createBoard(userId, request), 201);
    }

    @PutMapping("/{boardId}")
    public ApiResponse<BoardDetailResponse> updateBoard(@PathVariable Long boardId,
                                                        @RequestBody BoardWriteRequest request,
                                                        @AuthPrincipal Long userId) {
        return ApiResponse.success(boardService.updateBoard(userId, boardId, request), 200);
    }
}
