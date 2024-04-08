package org.example.cs_task.service.metric;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LongTaskTimerMetric {
  TASK_DELAY_TIME("Время задержки перед запуском");

  private final String description;
}
