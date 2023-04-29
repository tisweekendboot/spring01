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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  //jpa

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

  @DeleteMapping("/del/{no}")
  public ResponseEntity<Object> delete(@PathVariable("no") int no) {
    log.info("no===={}", no);
    int result = todoService.deleteTodoPath(no);
    if (result > 0) {
      Map<String, String> resultMap = new HashMap<>();
      resultMap.put("result", "ok");
      return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }
    Map<String, String> resultMap = new HashMap<>();
    resultMap.put("error", "잘못된 값입니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
  }

  @PutMapping("/modify/{no}")
  public ResponseEntity<Object> modify(@PathVariable("no") int no, int done) {
    TodoDto todoDto = new TodoDto();
    todoDto.setNo(no);
    todoDto.setDone(done);
    int result = todoService.modifyTodo(todoDto);
    log.info("todoDto==={}", todoDto);
    if (result > 0) {
      Map<String, String> resultMap = new HashMap<>();
      resultMap.put("result", "ok");
      return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }
    Map<String, String> resultMap = new HashMap<>();
    resultMap.put("error", "잘못된 값입니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
  }

  @GetMapping("/list/{pickedDate}")
  public ResponseEntity<?> list(@PathVariable("pickedDate") String pickedDate) {
    log.info("pickedDate==={}", pickedDate);
    List<TodoDto> todoList = todoService.getTodList(pickedDate);
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("todoList", (List<TodoDto>) todoList);
    return ResponseEntity.status(HttpStatus.OK).body(resultMap);
  }
}
