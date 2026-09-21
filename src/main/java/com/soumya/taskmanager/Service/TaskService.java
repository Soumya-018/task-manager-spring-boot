package com.soumya.taskmanager.Service;

import com.soumya.taskmanager.Dto.TaskDto;
import com.soumya.taskmanager.Entity.Task;
import com.soumya.taskmanager.Exception.TaskNotFoundException;
import com.soumya.taskmanager.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskDto createTask(TaskDto taskDto){
        Task task = modelMapper.map(taskDto,Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);
    }

    public List<TaskDto> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> modelMapper.map(task,TaskDto.class)).toList();
    }

    public TaskDto updateTask(Long id , TaskDto updateTask){
        Task task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));

        modelMapper.map(updateTask,task);

        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);

    }

    public void deleteTask(Long id){
        Task existingTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
        taskRepository.delete(existingTask);

    }

    public List<TaskDto> getCompletedTask(){
        List<Task> tasks = taskRepository.findByCompleted(true);

        return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
    }

    public List<TaskDto> searchTasks(String s){
        List<Task> tasks = taskRepository.findByTitleContaining("s");
        return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
    }
}
