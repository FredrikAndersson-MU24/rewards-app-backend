package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.CustomUserDetails;
import com.fredande.rewardsappbackend.dto.TaskCreationRequest;
import com.fredande.rewardsappbackend.dto.TaskSavedResponse;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.model.User;
import com.fredande.rewardsappbackend.repository.TaskRepository;
import com.fredande.rewardsappbackend.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskSavedResponse create(TaskCreationRequest taskCreationRequest, CustomUserDetails userDetails) {
        User user = new User();
        user.setId(userDetails.getId());
        TaskSavedResponse taskSavedResponse = new TaskSavedResponse(taskCreationRequest.getTitle(), taskCreationRequest.getDescription());
        Task task = new Task();
        task.setTitle(taskCreationRequest.getTitle());
        task.setDescription(taskCreationRequest.getDescription());
        task.setPoints(taskCreationRequest.getPoints());
        task.setUser(user);
        taskRepository.save(task);
        return taskSavedResponse;
    }

    public @Nullable List<Task> getTasksByUser(CustomUserDetails userDetails) {
        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        List<Task> tasks = taskRepository.findByUser(user).orElse(null);
        return tasks;
    }

}
