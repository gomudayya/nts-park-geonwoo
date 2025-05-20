package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.User;

public record SignUpResponse(
        String username,
        String nickname
) {
    public static SignUpResponse from(User user) {
        return new SignUpResponse(user.getUsername(), user.getNickname());
    }
}
