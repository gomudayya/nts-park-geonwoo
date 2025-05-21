package com.nts.ntsboard.controller.response;

import com.nts.ntsboard.domain.User;

public record UserInfoResponse(
        String username,
        String nickname
) {
    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(user.getUsername(), user.getNickname());
    }
}
