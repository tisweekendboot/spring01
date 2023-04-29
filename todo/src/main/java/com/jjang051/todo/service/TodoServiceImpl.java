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

  @Override
  public List<TodoDto> getTodList(String pickedDate) {
    return todoDao.getTodoList(pickedDate);
  }

  @Override
  public int deleteTodoPath(int no) {
    return todoDao.deleteTodoPath(no);
  }

  @Override
  public int modifyTodo(TodoDto todoDto) {
    return todoDao.modifyTodo(todoDto);
  }
}
