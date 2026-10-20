package com.wangley.task.service;

import com.wangley.task.exception.TaskNotFoundException;
import com.wangley.task.model.Task;
import com.wangley.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task createTask() {
        return new Task(
                1L,"Task 1",false
        );
    }

    @Test
    void shouldReturnAllTasks() {

        List<Task> tasks = List.of(
                new Task(1L, "Task 1", false),
                new Task(2L, "Task 2", true)
        );

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.listTask();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
        assertEquals("Task 2", result.get(1).getTitle());

        verify(taskRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoTasksExist() {

        when(taskRepository.findAll()).thenReturn(List.of());

        List<Task> result = taskService.listTask();

        assertEquals(0, result.size());

        verify(taskRepository).findAll();
    }

    @Test
    void shouldReturnTaskWhenIdExists() {

        Task task = createTask();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.findById(1L);

        assertEquals("Task 1", result.getTitle());
        assertEquals(false, result.isCompleted());

        verify(taskRepository).findById(1L);
    }

    @Test
    void mustThrowExceptionWhenIdDoesNotExist() {

        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        TaskNotFoundException exception = assertThrows(
                TaskNotFoundException.class,
                () -> taskService.findById(999L)
        );

        assertEquals("Task not found with id: 999", exception.getMessage());

        verify(taskRepository).findById(999L);
    }

    @Test
    void shouldCreateTask() {

        Task task = createTask();

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.create(task);

        assertEquals("Task 1", result.getTitle());
        assertEquals(false, result.isCompleted());

        verify(taskRepository).save(task);
    }

    @Test
    void shouldUpdateExistingTask() {
        Task task = createTask();

        Task dadosAtualizados = new Task(
                "Task 1",
                true
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.update(1L, dadosAtualizados);

        assertEquals(1L, result.getId());
        assertEquals("Task 1", result.getTitle());
        assertEquals(true, result.isCompleted());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentTask() {

        when(taskRepository.findById(999L))
                .thenReturn(Optional.empty());

        Task updatedTask = new Task(
                "Updated Task",
                true
        );

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.update(999L, updatedTask)
        );

        verify(taskRepository).findById(999L);

        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void shouldDeleteExistingTask() {

        Task task = createTask();

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.delete(1L);

        verify(taskRepository).findById(1L);
        verify(taskRepository).delete(task);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentTask() {

        when(taskRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.delete(999L)
        );

        verify(taskRepository).findById(999L);
        verify(taskRepository, never()).delete(any(Task.class));
    }

}
