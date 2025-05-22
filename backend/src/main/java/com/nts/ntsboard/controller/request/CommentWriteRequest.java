package com.nts.ntsboard.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentWriteRequest(

        @NotBlank(message = "댓글 내용을 입력해주세요.")
        @Size(max = 100, message = "댓글은 최대 100자까지 입력할 수 있습니다.")
        String content
) {
}