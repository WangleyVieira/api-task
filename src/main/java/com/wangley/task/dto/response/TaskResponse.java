package com.wangley.task.dto.response;

import com.wangley.task.model.Task;

import java.time.LocalDateTime;

public record TaskResponse(
    Long id,
    String title,
    boolean completed,
    LocalDateTime createdAt
) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.isCompleted(),
            task.getCreatedAt()
        );
    }
}
