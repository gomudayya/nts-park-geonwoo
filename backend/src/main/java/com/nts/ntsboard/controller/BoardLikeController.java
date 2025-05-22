package com.nts.ntsboard.controller;

import com.nts.ntsboard.common.auth.AuthPrincipal;
import com.nts.ntsboard.common.response.ApiResponse;
import com.nts.ntsboard.controller.response.CountResponse;
import com.nts.ntsboard.controller.response.LikeStatusResponse;
import com.nts.ntsboard.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards/{boardId}/likes")
@RequiredArgsConstructor
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<Void> like(@AuthPrincipal Long userId, @PathVariable Long boardId) {
        boardLikeService.like(userId, boardId);
        return ApiResponse.success(null, 201);
    }

    @DeleteMapping
    public ApiResponse<Void> unlike(@AuthPrincipal Long userId, @PathVariable Long boardId) {
        boardLikeService.unlike(userId, boardId);
        return ApiResponse.success(null, 200);
    }

    @GetMapping("/count")
    public ApiResponse<CountResponse> getLikeCount(@PathVariable Long boardId) {
        return ApiResponse.success(boardLikeService.getLikeCount(boardId), 200);
    }

    @GetMapping("/me")
    public ApiResponse<LikeStatusResponse> getMyLikeStatus(
            @AuthPrincipal Long userId,
            @PathVariable Long boardId
    ) {
        return ApiResponse.success(boardLikeService.getLikeStatus(userId, boardId), 200);
    }
}