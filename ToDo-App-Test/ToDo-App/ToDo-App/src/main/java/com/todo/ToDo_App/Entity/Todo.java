package com.todo.ToDo_App.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    private Long id;


    @NotNull
    @Schema(name = "Title",example = "Title mention")
    private String title;


//    @Size(min = 5,max = 10)
//    String description;

    @JsonProperty("isCompleted")
    private boolean isCompleted;


//    @Email
//    String email;
//
//
//    @Pattern(regexp = "^[0-9]{10}$")
//    String mobile_no;
}
