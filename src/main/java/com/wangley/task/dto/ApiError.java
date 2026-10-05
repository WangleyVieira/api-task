package com.wangley.task.dto;

public record ApiError(
        int status,
        String erro,
        String message
) {
}
