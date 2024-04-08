package org.example.cs_task.excepton;

public class TaskNameNotExistException extends RuntimeException {

  public TaskNameNotExistException(String message) {
    super(message);
  }
}
