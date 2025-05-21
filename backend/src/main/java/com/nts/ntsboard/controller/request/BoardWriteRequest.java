package com.nts.ntsboard.controller.request;

import java.util.List;

public record BoardWriteRequest(
        String title,
        String content,
        List<String> hashtags
) {
}