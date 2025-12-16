package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.model.User;
import com.fredande.rewardsappbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updatePoints(Integer userId, Integer points, boolean done) {
        User savedUser = userRepository.findById(userId).orElseThrow();
        if (done) {
            savedUser.setCurrentPoints(savedUser.getCurrentPoints() + points);
            savedUser.setTotalPoints(savedUser.getTotalPoints() + points);
        } else {
            savedUser.setCurrentPoints(savedUser.getCurrentPoints() - points);
            savedUser.setTotalPoints(savedUser.getTotalPoints() - points);
        }
    }


}
