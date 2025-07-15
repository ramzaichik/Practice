package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
  private Long id;
  private String description;
  private boolean completed;
  private String name;
  private int progress;
}
