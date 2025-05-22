package com.nts.ntsboard.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NotBlank(message = "아이디를 입력해주세요.")
        @Size(max = 20, message = "아이디는 20자 이하로 입력해주세요.")
        String username,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(max = 20, message = "비밀번호는 20자 이하로 입력해주세요.")
        String password,

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(max = 20, message = "닉네임은 20자 이하로 입력해주세요.")
        String nickname

) {
}