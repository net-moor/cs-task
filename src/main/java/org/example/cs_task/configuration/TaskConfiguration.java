package org.example.cs_task.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "cs-task")
public class TaskConfiguration {

  private final Long taskDelayMs;

  private final Set<String> tasksName;
}
