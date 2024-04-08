package org.example.cs_task.service;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cs_task.configuration.TaskConfiguration;
import org.example.cs_task.excepton.TaskNameNotExistException;
import org.example.cs_task.model.Task;
import org.example.cs_task.service.metric.CounterMetric;
import org.example.cs_task.service.metric.LongTaskTimerMetric;
import org.example.cs_task.service.metric.MetricService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskConfiguration taskConfiguration;

  private final MetricService metricService;

  private final DelayQueue<Task> taskQueue = new DelayQueue<>();

  private final ExecutorService executorService = Executors.newCachedThreadPool();

  public void executeTask(String taskName) {
    metricService.incrementCounter(CounterMetric.TASK_INCOME_TOTAL);
    if (taskConfiguration.getTasksName().contains(taskName)) {
      var timer = metricService.getLongTaskTimerMetric(LongTaskTimerMetric.TASK_DELAY_TIME).start();
      var task = new Task(taskName, taskConfiguration.getTaskDelayMs(), executeTask -> timer.stop());
      taskQueue.offer(task);
      metricService.incrementCounter(CounterMetric.TASK_PROCESS_TOTAL);
    } else {
      metricService.incrementCounter(CounterMetric.TASK_EXCLUDED_TOTAL);
      throw new TaskNameNotExistException(String.format("Task '%s' not exist", taskName));
    }
  }

  @Scheduled(fixedDelay = 500)
  public void tasksScheduler() {
    Task task;
    while ((task = taskQueue.poll()) != null) {
      CompletableFuture.runAsync(task, executorService)
              .whenComplete((res, throwable) -> metricService.incrementCounter(CounterMetric.TASK_EXECUTED_TOTAL));
    }
  }

  @PreDestroy
  public void preDestroy() {
    try {
      executorService.shutdown();
      var termination = executorService.awaitTermination(1, TimeUnit.MINUTES);
      if (termination) {
        log.info("All running tasks are completed.");
      }
    } catch (InterruptedException e) {
      log.warn("The waiting time for completing task has error.");
      Thread.currentThread().interrupt();
    }
  }
}
