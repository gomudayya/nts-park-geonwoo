package com.nts.ntsboard.controller.request;

import java.util.List;

public record BoardCreateRequest(
        String content,
        List<String> hashtags
) {
}
