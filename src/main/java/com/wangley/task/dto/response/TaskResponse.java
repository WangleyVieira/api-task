package com.wangley.task.dto.response;

import com.wangley.task.model.Task;

public record TaskResponse(
    Long id,
    String title,
    boolean completed
) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.isCompleted()
        );
    }
}
