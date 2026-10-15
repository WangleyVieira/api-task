package com.wangley.task.controller;

import com.wangley.task.dto.request.CreateTaskRequest;
import com.wangley.task.dto.request.UpdateTaskRequest;
import com.wangley.task.dto.response.TaskResponse;
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
    public List<TaskResponse> listTask() {
        return taskService.listTask()
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    @GetMapping("/api/tasks/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return TaskResponse.from(taskService.findById(id));
    }

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        Task task = new Task(
                request.title(),
                request.completed()
        );

        return TaskResponse.from(taskService.create(task));
    }

    @PutMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request
    ) {
        Task updatedTask = new Task(
                request.title(),
                request.completed()
        );
        return TaskResponse.from(taskService.update(id, updatedTask));
    }

    @DeleteMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
