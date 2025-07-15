package com.example.practice.controller;

import com.example.practice.dto.TaskDto;
import com.example.practice.mapper.TaskMapper;
import com.example.practice.model.Task;
import com.example.practice.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final TaskService taskService;
  private final TaskMapper taskMapper;

  public TaskController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @GetMapping
  public List<TaskDto> getAllTasks() {
    return taskMapper.toDtoList(taskService.getAllTasks());
  }

  @PostMapping
  public TaskDto addTask(@RequestBody TaskDto taskDto) {
    Task task = taskService.addTask(taskDto);
    return taskMapper.toDto(task);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }

  @PatchMapping("/toggle/{id}")
  public TaskDto toggleTask(@PathVariable Long id) {
    Task task = taskService.toggleTask(id);
    return taskMapper.toDto(task);
  }

  @PatchMapping("/{id}/progress")
  public TaskDto updateProgress(@PathVariable Long id, @RequestBody int progress) {
    Task updatedTask = taskService.updateProgress(id, progress);
    return taskMapper.toDto(updatedTask);
  }
}
