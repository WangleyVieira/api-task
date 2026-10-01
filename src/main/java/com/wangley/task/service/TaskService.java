package com.wangley.task.service;

import com.wangley.task.dto.CreateTaskRequest;
import com.wangley.task.exception.TaskNotFoundException;
import com.wangley.task.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    // This is a list of tasks that is initialized with some sample data.
    private final List<Task> tasks = new ArrayList<>(
            List.of(
                    new Task(1L, "Task 1", false),
                    new Task(2L, "Task 2", true),
                    new Task(3L, "Task 3", false)
            )
    );

    // This method returns a copy of the list of tasks.
    public List<Task> listTask() {
        return List.copyOf(tasks);
    }

    // This method finds a task by its ID.
    public Task findById(Long id) {
        return listTask()
                .stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));
    }

    // This method creates a new task with the given title and completed status.
    public Task create(String title, boolean completed) {
        Long nextId = tasks.stream()
                .mapToLong(Task::getId)
                .max().orElse(0L) + 1;

        Task newTask = new Task(nextId, title, completed);

        tasks.add(newTask);

        return newTask;
    }

}
