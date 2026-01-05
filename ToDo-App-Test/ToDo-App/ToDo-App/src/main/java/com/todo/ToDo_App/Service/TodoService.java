package com.todo.ToDo_App.Service;

import com.todo.ToDo_App.Repositary.TodoRepository;
import com.todo.ToDo_App.Entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepositary;

    public Todo createTodo(Todo todo) {
         todoRepositary.save(todo);
         return todo;
    }

    public Todo getTodoById(Long id){
         return todoRepositary.findById(id).orElseThrow(()-> new RuntimeException("Todo Not Found"));
    }

    public Page<Todo> getTodoPages(int page,int size){
         Pageable pageable= PageRequest.of(page,size);
         return todoRepositary.findAll(pageable);
    }

    public List<Todo> getTodos(){
         return todoRepositary.findAll();
     }

    public Todo updateTodo(Todo todo){
         return todoRepositary.save(todo);
     }

    public void deleteTodoById(Long id){
         todoRepositary.delete(getTodoById(id));
     }

    public void deleteTodo(Todo todo){
         todoRepositary.delete(todo);
     }
}
