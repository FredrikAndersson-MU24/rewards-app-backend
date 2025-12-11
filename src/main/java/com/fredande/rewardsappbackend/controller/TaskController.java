package com.fredande.rewardsappbackend.controller;

import com.fredande.rewardsappbackend.CustomUserDetails;
import com.fredande.rewardsappbackend.dto.TaskCreationRequest;
import com.fredande.rewardsappbackend.dto.TaskSavedResponse;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> hello(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(taskService.getTasksByUser(userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskReadResponse> getTaskByIdAndUser(@PathVariable Integer id,
                                                               @AuthenticationPrincipal CustomUserDetails userDetails) throws BadRequestException {
        return ResponseEntity.status(200).body(taskService.getTaskByIdAndUser(id, userDetails));
    }

    @PostMapping
    public ResponseEntity<TaskSavedResponse> create(@RequestBody @Valid TaskCreationRequest taskCreationRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        TaskSavedResponse savedTask = taskService.create(taskCreationRequest, userDetails);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskSavedResponse> update(@PathVariable Integer id,
                                                    @RequestBody @Valid TaskUpdateRequest updatedTask,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) throws BadRequestException {
        return ResponseEntity.status(201).body(taskService.update(id, userDetails, updatedTask));
    }


}
