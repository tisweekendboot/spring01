package com.jjang051.jpa.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = RuntimeException.class)
  public Map<String, String> handleException(Exception e) {
    Map<String, String> map = new HashMap<>();
    map.put("error", e.getMessage());
    return map;
  }
}
