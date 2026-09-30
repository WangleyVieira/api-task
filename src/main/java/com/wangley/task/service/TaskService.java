package com.wangley.task.service;

import com.wangley.task.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    // This is a simple service that returns a list of tasks.
    public List<Task> listTask() {
        return List.of(
                new Task(1L, "Task 1", false),
                new Task(2L, "Task 2", true),
                new Task(3L, "Task 3", false)
        );
    }

    // This method finds a task by its ID.
    public Task findById(Long id) {
        return listTask()
                .stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
