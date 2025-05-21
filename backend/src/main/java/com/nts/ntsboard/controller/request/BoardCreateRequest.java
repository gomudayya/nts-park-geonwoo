package com.nts.ntsboard.controller.request;

import java.util.List;

public record BoardCreateRequest(
        String title,
        String content,
        List<String> hashtags
) {
}
