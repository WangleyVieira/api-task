package com.wangley.task.controller;

import com.wangley.task.dto.CreateTaskRequest;
import com.wangley.task.dto.UpdateTaskRequest;
import com.wangley.task.model.Task;
import com.wangley.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/tasks/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        return taskService.create(
                request.title(),
                request.completed()
        );
    }

    @PutMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request
    ) {
        return taskService.update(
                id,
                request.title(),
                request.completed()
        );
    }

    @DeleteMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
