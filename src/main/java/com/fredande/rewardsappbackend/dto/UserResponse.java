package com.fredande.rewardsappbackend.dto;

import org.springframework.web.util.HtmlUtils;

public record UserResponse(String email,
                           String firstName,
                           String lastName,
                           Integer currentPoints,
                           Integer totalPoints,
                           Integer numTasksOpen,
                           Integer numTasksCompleted,
                           Integer numTasksTotal) {

    public UserResponse(String email,
                        String firstName,
                        String lastName,
                        Integer currentPoints,
                        Integer totalPoints,
                        Integer numTasksOpen,
                        Integer numTasksCompleted,
                        Integer numTasksTotal) {
        this.email = email;
        this.firstName = HtmlUtils.htmlEscape(firstName);
        this.lastName = HtmlUtils.htmlEscape(lastName);
        this.currentPoints = currentPoints;
        this.totalPoints = totalPoints;
        this.numTasksOpen = numTasksOpen;
        this.numTasksCompleted = numTasksCompleted;
        this.numTasksTotal = numTasksTotal;


    }

}
