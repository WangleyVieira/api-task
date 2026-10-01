package com.wangley.task.dto;

public record CreateTaskRequest(
        String title,
        boolean completed
) {
}
