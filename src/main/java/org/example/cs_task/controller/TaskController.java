package org.example.cs_task.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.example.cs_task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @Timed
  @PostMapping("/execute/{taskName}")
  public ResponseEntity<Void> executeTask(@PathVariable String taskName) {
    taskService.executeTask(taskName);
    return ResponseEntity.noContent().build();
  }
}
