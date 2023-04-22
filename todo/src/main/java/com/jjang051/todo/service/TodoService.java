package com.jjang051.todo.service;

import com.jjang051.todo.dto.TodoDto;
import java.util.List;

public interface TodoService {
  int insertTodo(TodoDto todoDto);
  List<TodoDto> getTodo(TodoDto todoDto);
  int deleteTodo(TodoDto todoDto);
}
