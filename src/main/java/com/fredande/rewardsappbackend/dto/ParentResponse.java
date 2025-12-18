package com.fredande.rewardsappbackend.dto;

import com.fredande.rewardsappbackend.model.User;

import java.util.List;

public record ParentResponse(Integer id,
                             String email,
                             String firstName,
                             String lastName,
                             List<User> children) {

}
