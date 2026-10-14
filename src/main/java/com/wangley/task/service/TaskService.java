package com.wangley.task.service;

import com.wangley.task.exception.TaskNotFoundException;
import com.wangley.task.model.Task;
import com.wangley.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // This is a list of tasks that is initialized with some sample data.
    public List<Task> listTask() {
        return taskRepository.findAll();
    }


    // This method finds a task by its ID.
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    // This method creates a new task with the given title and completed status.
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    // This method updates an existing task with the given ID, title, and completed status.
    public Task update(Long id, Task updatedTask) {
        Task task = findById(id);

        Task taskToUpdate = new Task(
                task.getId(),
                updatedTask.getTitle(),
                updatedTask.isCompleted()
        );

        return taskRepository.save(taskToUpdate);
    }

    // This method deletes a task with the given ID.
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
