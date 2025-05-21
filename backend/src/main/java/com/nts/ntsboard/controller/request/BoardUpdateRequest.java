package com.nts.ntsboard.controller.request;

import java.util.List;

public record BoardUpdateRequest(
        String title,
        String content,
        List<String> hashtags
) {
}