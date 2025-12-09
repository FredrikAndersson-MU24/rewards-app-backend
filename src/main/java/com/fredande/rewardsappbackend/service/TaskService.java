package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.dto.TaskSavedResponse;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public TaskSavedResponse create(@Valid Task task) {
        TaskSavedResponse taskSavedResponse = new TaskSavedResponse(task.getTitle(), task.getDescription());
        taskRepository.save(task);
        return taskSavedResponse;
    }

}
