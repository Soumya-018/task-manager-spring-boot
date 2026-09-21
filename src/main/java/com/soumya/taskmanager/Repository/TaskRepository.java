package com.soumya.taskmanager.Repository;

import com.soumya.taskmanager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByCompleted(boolean Completed);
    List<Task> findByTitleContaining(String s);



}
