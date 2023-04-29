package com.jjang051.todo.dao;

import com.jjang051.todo.dto.TodoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoDao {
  int insertTodo(TodoDto todoDto);
  List<TodoDto> getTodo(TodoDto todoDto);
  int deleteTodo(TodoDto todoDto);
  List<TodoDto> getTodoList(String pickedDate);
  int deleteTodoPath(int no);
}
