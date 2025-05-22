package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.User;

public record UserInfoResponse(
        Long userId,
        String username,
        String nickname
) {
    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(user.getId(), user.getUsername(), user.getNickname());
    }
}
