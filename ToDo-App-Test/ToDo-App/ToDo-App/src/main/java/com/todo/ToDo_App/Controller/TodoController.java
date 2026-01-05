package com.todo.ToDo_App.Controller;

import com.todo.ToDo_App.Service.TodoService;
import com.todo.ToDo_App.Entity.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoservice;

    @PostMapping("/create")
    public ResponseEntity<Todo> createUser(@RequestBody Todo todo){
        return new ResponseEntity<>(todoservice.createTodo(todo), HttpStatus.CREATED);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo Found"),
            @ApiResponse(responseCode = "404",description = "Todo Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getId(@PathVariable Long id){
        try{
            Todo createdId=todoservice.getTodoById(id);
            return new ResponseEntity<>(createdId,HttpStatus.OK);
        } catch (RuntimeException exception){
            log.error("Id Not Found",exception);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<>(todoservice.getTodos(),HttpStatus.OK);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<Todo>> getAllTodoPages(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(todoservice.getTodoPages(page,size),HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Todo> updateTodoByTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoservice.updateTodo(todo),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteTodoByTodo(@PathVariable Long id){
        todoservice.deleteTodoById(id);
    }


    @DeleteMapping
    public void deleteTodoByTodo(@RequestBody Todo todo){
         todoservice.deleteTodo(todo);
    }
}
