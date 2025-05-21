package com.nts.ntsboard.controller;

import com.nts.ntsboard.common.auth.AuthPrincipal;
import com.nts.ntsboard.common.response.ApiResponse;
import com.nts.ntsboard.common.response.PageResponse;
import com.nts.ntsboard.controller.request.BoardWriteRequest;
import com.nts.ntsboard.controller.response.BoardDetailResponse;
import com.nts.ntsboard.controller.response.BoardSummaryResponse;
import com.nts.ntsboard.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<BoardDetailResponse> createBoard(@RequestBody @Valid BoardWriteRequest request,
                                                        @AuthPrincipal Long userId) {
        return ApiResponse.success(boardService.createBoard(userId, request), 201);
    }

    @PutMapping("/{boardId}")
    public ApiResponse<BoardDetailResponse> updateBoard(@PathVariable Long boardId,
                                                        @RequestBody @Valid BoardWriteRequest request,
                                                        @AuthPrincipal Long userId) {
        return ApiResponse.success(boardService.updateBoard(userId, boardId, request), 200);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{boardId}")
    public ApiResponse<Void> deleteBoard(@PathVariable Long boardId, @AuthPrincipal Long userId) {
        boardService.deleteBoard(userId, boardId);
        return ApiResponse.success(null, 204);
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardDetailResponse> getBoard(@PathVariable Long boardId) {
        return ApiResponse.success(boardService.getBoard(boardId), 200);
    }

    @GetMapping
    public ApiResponse<PageResponse<BoardSummaryResponse>> getBoards(Pageable pageable) {
        return ApiResponse.success(boardService.getBoardByPage(pageable), 200);
    }

}
