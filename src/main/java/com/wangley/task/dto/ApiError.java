package com.wangley.task.dto;

import java.util.List;

public record ApiError(
        int status,
        String erro,
        List<String> messages
) {
}
