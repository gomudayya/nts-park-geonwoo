package com.nts.ntsboard.controller.request;

public record SignUpRequest(
        String username,
        String password,
        String nickname
) {}
