package com.example.practice.mapper;

import com.example.practice.dto.TaskDto;
import com.example.practice.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
  TaskDto toDto(Task task);

  Task toEntity(TaskDto taskDto);

  List<TaskDto> toDtoList(List<Task>tasks);

}
