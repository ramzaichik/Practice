package com.example.practice.service;

import com.example.practice.dto.TaskDto;
import com.example.practice.mapper.TaskMapper;
import com.example.practice.model.Task;
import com.example.practice.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task addTask(TaskDto taskDto) {
    return taskRepository.save(taskMapper.toEntity(taskDto));
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  public Task toggleTask(Long id) {
    Task task = taskRepository.findById(id).orElseThrow();
    task.setCompleted(!task.isCompleted());
    return taskRepository.save(task);
  }

  public Task updateProgress(Long id, int progress) {
    Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    if (progress < 0 || progress > 100) {
      throw new IllegalArgumentException("Progress must be between 0 and 100");
    }
    task.setProgress(progress);
    if (progress == 100) {
      task.setCompleted(true);
    } else {
      task.setCompleted(false);
    }
    return taskRepository.save(task);
  }
}
