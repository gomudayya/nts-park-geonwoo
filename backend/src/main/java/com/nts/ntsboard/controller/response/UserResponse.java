package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.User;

public record UserResponse(
        String username,
        String nickname
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getUsername(), user.getNickname());
    }
}
