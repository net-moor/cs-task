package org.example.cs_task.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class Task implements Delayed, Runnable {

  private final long createTime = System.currentTimeMillis();

  private final String taskName;

  private final long taskDelay;

  private final Consumer<Task> beforeExecute;

  @Override
  public long getDelay(TimeUnit unit) {
    long diff = createTime + taskDelay - System.currentTimeMillis();
    return unit.convert(diff, TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    return (int) (this.createTime - ((Task) o).createTime);
  }

  @Override
  public void run() {
    beforeExecute.accept(this);
    var createdlocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(createTime), TimeZone.getDefault().toZoneId());
    log.info("Task: '{}' created at {} executed at {}", taskName, createdlocalDateTime, LocalDateTime.now());
  }
}
