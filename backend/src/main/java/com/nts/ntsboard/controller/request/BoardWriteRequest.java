package com.nts.ntsboard.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record BoardWriteRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 최대 100자까지 입력할 수 있습니다.")
        String title,

        @NotBlank(message = "본문은 필수입니다.")
        String content,
        @NotNull
        @Size(max = 5, message = "해시태그는 최대 5개까지 입력할 수 있습니다.")
        List<@NotBlank(message = "해시태그는 공백일 수 없습니다.") String> hashtags
) {
}