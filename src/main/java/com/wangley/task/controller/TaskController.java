package com.wangley.task.controller;

import com.wangley.task.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/api/tasks")
    public List<Task> listTask() {

        return List.of(
                new Task(1L, "Task 1", false),
                new Task(2L, "Task 2", true),
                new Task(3L, "Task 3", false)
        );
    }
}
