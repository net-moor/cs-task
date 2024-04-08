package org.example.cs_task.service.metric;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CounterMetric {

  TASK_INCOME_TOTAL("Общее количество поступивших задач"),
  TASK_PROCESS_TOTAL("Общее количество взятых в обработку задач"),
  TASK_EXCLUDED_TOTAL("Общее количество отклоненных задач"),
  TASK_EXECUTED_TOTAL("Общее количество завершённых задач");

  private final String description;
}
