package com.soumya.taskmanager.Controller;

import com.soumya.taskmanager.Dto.TaskDto;
import com.soumya.taskmanager.Entity.Task;
import com.soumya.taskmanager.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    @GetMapping
    public List<TaskDto> getAllTasks(){
        return  taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto updateTask){
      return taskService.updateTask(id,updateTask);
    }


    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/search")
    public List<TaskDto> searchTasks(@RequestParam String keyword){
        return taskService.searchTasks(keyword);
    }

}
