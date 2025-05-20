package com.nts.ntsboard.controller.request;

public record LoginRequest(
        String username,
        String password
) {}
