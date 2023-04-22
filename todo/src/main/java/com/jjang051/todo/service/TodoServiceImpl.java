package com.jjang051.todo.service;

import com.jjang051.todo.dao.TodoDao;
import com.jjang051.todo.dto.TodoDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

  @Autowired
  TodoDao todoDao;

  @Override
  public List<TodoDto> getTodo(TodoDto todoDto) {
    return todoDao.getTodo(todoDto);
  }

  @Override
  public int insertTodo(TodoDto todoDto) {
    return todoDao.insertTodo(todoDto);
  }

  @Override
  public int deleteTodo(TodoDto todoDto) {
    return todoDao.deleteTodo(todoDto);
  }
}
