package com.wangley.task.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(

        @NotBlank(message = "Title is required")
        String title,

        boolean completed
) {
}
