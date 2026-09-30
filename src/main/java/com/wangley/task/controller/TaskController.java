package com.wangley.task.controller;

import com.wangley.task.model.Task;
import com.wangley.task.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/tasks")
    public List<Task> listTask() {
        return taskService.listTask();
    }

    @GetMapping("api/tasks/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }
}
