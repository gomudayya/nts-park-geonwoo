package com.nts.ntsboard.controller;

import com.nts.ntsboard.common.auth.AuthPrincipal;
import com.nts.ntsboard.common.response.ApiResponse;
import com.nts.ntsboard.common.response.SliceResponse;
import com.nts.ntsboard.controller.request.CommentWriteRequest;
import com.nts.ntsboard.controller.response.CommentResponse;
import com.nts.ntsboard.service.CommentService;
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
@RequestMapping("/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CommentResponse> createComment(
            @AuthPrincipal Long userId,
            @PathVariable Long boardId,
            @RequestBody @Valid CommentWriteRequest request
    ) {
        return ApiResponse.success(commentService.createComment(userId, boardId, request), 201);
    }

    @PutMapping("/{commentId}")
    public ApiResponse<CommentResponse> updateComment(
            @AuthPrincipal Long userId,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentWriteRequest request
    ) {
        return ApiResponse.success(commentService.updateComment(userId, commentId, request), 200);
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<CommentResponse> deleteComment(
            @AuthPrincipal Long userId,
            @PathVariable Long commentId
    ) {
        return ApiResponse.success(commentService.deleteComment(userId, commentId), 200);
    }

    @GetMapping
    public ApiResponse<SliceResponse<CommentResponse>> getComments(
            @PathVariable Long boardId,
            Pageable pageable
    ) {
        return ApiResponse.success(commentService.getCommentBySlice(boardId, pageable), 200);
    }
}
