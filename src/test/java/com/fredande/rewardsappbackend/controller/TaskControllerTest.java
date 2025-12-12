package com.fredande.rewardsappbackend.controller;

import com.fredande.rewardsappbackend.CustomUserDetails;
import com.fredande.rewardsappbackend.dto.TaskCreationRequest;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.model.User;
import com.fredande.rewardsappbackend.repository.TaskRepository;
import com.fredande.rewardsappbackend.service.TaskService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class TaskControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    ObjectMapper objectMapper;

    @WithMockUser
    @Test
    void create_valid() throws Exception {
        // Arrange
        String title = "This is the title";
        String description = "Here is the description";
        Integer points = 10;
        User user = new User();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        TaskCreationRequest request = new TaskCreationRequest(title, description, points);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // Act & Assert
        mvc.perform(post("/api/tasks")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }

    @WithMockUser
    @Test
    void create_invalid_title_empty() throws Exception {
        // Arrange
        String title = "";
        String description = "Here is the description";
        Integer points = 10;
        User user = new User();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        TaskCreationRequest request = new TaskCreationRequest(title, description, points);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // Act & Assert
        mvc.perform(post("/api/tasks")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }

    @WithMockUser
    @Test
    void create_invalid_points_negativeNumber() throws Exception {
        // Arrange
        String title = "This is the title";
        String description = "Here is the description";
        Integer points = -10;
        User user = new User();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        TaskCreationRequest request = new TaskCreationRequest(title, description, points);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // Act & Assert
        mvc.perform(post("/api/tasks")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Points must be a whole number of zero or greater"));

    }

    @Test
    void getAllTasksByUser() {
    }

    @Test
    void getTaskByIdAndUser() {
    }


    @Test
    void update() {
    }

}