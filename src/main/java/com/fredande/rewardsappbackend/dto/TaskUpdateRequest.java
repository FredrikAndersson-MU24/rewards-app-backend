package com.fredande.rewardsappbackend.dto;

public record TaskUpdateRequest(String title, String description, Integer points, boolean done) {

}
