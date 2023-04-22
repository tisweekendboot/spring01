package com.jjang051.todo.controller;

import com.jjang051.todo.dto.TodoDto;
import com.jjang051.todo.service.TodoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Controller
@RequestMapping("/todo")
public class TodoController {

  @Autowired
  TodoService todoService;

  @GetMapping("/index")
  public String home() {
    return "/todo/index";
  }

  @PostMapping("/add")
  public ResponseEntity<Object> add(TodoDto todoDto) {
    //log.info("todoDto===={}", todoDto);
    int result = todoService.insertTodo(todoDto);
    if (result > 0) {
      List<TodoDto> todoList = todoService.getTodo(todoDto);
      return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }
    Map<String, String> resultMap = new HashMap<>();
    resultMap.put("error", "잘못된 값입니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
  }

  @PostMapping("/del")
  public ResponseEntity<Object> delete(TodoDto todoDto) {
    int result = todoService.deleteTodo(todoDto);
    if (result > 0) {
      Map<String, String> resultMap = new HashMap<>();
      resultMap.put("result", "ok");
      return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }
    Map<String, String> resultMap = new HashMap<>();
    resultMap.put("error", "잘못된 값입니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
  }
}
