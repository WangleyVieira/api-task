package com.wangley.task.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskRequest(

        @NotBlank(message = "Title is required")
        String title,

        boolean completed
) {
}
