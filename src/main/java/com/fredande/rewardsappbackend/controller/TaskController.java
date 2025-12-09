package com.fredande.rewardsappbackend.controller;

import com.fredande.rewardsappbackend.dto.TaskSavedResponse;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<TaskSavedResponse> create(@RequestBody @Valid Task task) {
        TaskSavedResponse savedTask = taskService.create(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }


}
