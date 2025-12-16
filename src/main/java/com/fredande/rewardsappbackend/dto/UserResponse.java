package com.fredande.rewardsappbackend.dto;

public record UserResponse(String email,
                           String firstName,
                           String lastName,
                           Integer currentPoints,
                           Integer totalPoints,
                           Integer numTasksOpen,
                           Integer numTasksCompleted,
                           Integer numTasksTotal) {


}
