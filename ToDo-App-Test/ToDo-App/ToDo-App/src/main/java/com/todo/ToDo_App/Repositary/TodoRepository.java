package com.todo.ToDo_App.Repositary;

import com.todo.ToDo_App.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
