package org.example.cs_task.excepton;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {TaskNameNotExistException.class})
  protected ResponseEntity<Void> handleConflict(TaskNameNotExistException ex) {
    return ResponseEntity.badRequest().build();
  }
}
