package com.soumya.taskmanager.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;

    @Size(max=500, message = "description cannot exceeds 500 charecters")
    private String description;


    private LocalDate dueDate;
    private boolean completed;

}
