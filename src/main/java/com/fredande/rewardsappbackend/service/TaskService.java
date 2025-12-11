package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.CustomUserDetails;
import com.fredande.rewardsappbackend.dto.TaskCreationRequest;
import com.fredande.rewardsappbackend.dto.TaskReadResponse;
import com.fredande.rewardsappbackend.dto.TaskSavedResponse;
import com.fredande.rewardsappbackend.dto.TaskUpdateRequest;
import com.fredande.rewardsappbackend.mapper.TaskMapper;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.model.User;
import com.fredande.rewardsappbackend.repository.TaskRepository;
import com.fredande.rewardsappbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Task task = new Task();
        task.setTitle(taskCreationRequest.title());
        task.setDescription(taskCreationRequest.description());
        task.setPoints(taskCreationRequest.points());
        task.setUser(user);
        taskRepository.save(task);
        return TaskMapper.INSTANCE.taskToTaskSavedResponse(task);
    }

    public @Nullable List<TaskReadResponse> getAllTasksByUser(CustomUserDetails userDetails) {
        return taskRepository.findByUser(userRepository.findById(userDetails.getId()).orElseThrow())
                .stream()
                .map(TaskMapper.INSTANCE::taskToTaskReadResponse)
                .toList();
    }

    public TaskSavedResponse update(Integer id, CustomUserDetails userDetails, TaskUpdateRequest updatedTask) throws BadRequestException {
        Task savedTask = taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(userDetails.getId()).orElseThrow(EntityNotFoundException::new);
        boolean updated = false;
        if (!savedTask.getUser().equals(user)) {
            throw new BadRequestException("Task-user mismatch");
        }
        if (!savedTask.getTitle().equals(updatedTask.title())) {
            savedTask.setTitle(updatedTask.title());
            updated = true;
        }
        if (!savedTask.getDescription().equals(updatedTask.description())) {
            updated = true;
            savedTask.setDescription(updatedTask.description());
        }
        if (!savedTask.getPoints().equals(updatedTask.points())) {
            updated = true;
            savedTask.setPoints(updatedTask.points());
        }
        if (savedTask.isDone() != (updatedTask.done())) {
            updated = true;
            savedTask.setDone(updatedTask.done());
        }
        if (updated) {
            savedTask.setUpdated(new Date());
        }
        taskRepository.save(savedTask);
        return TaskMapper.INSTANCE.taskToTaskSavedResponse(savedTask);
    }

    public TaskReadResponse getTaskByIdAndUser(Integer id, CustomUserDetails userDetails) throws BadRequestException {
        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        Task savedTask = taskRepository.findByIdAndUser(id, user).orElse(null);
        if (savedTask == null) {
            throw new BadRequestException("User-task mismatch");
        }
        return TaskMapper.INSTANCE.taskToTaskReadResponse(savedTask);
    }

}
