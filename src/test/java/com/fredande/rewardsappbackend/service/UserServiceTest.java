package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.model.User;
import com.fredande.rewardsappbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    /**
     * If a task has been marked done (done==true), the tasks points should be added to the users totalPoints and
     * currentPoints.
     */
    @Test
    void update_valid_points_increase() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setCurrentPoints(10);
        user.setTotalPoints(20);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        // Act
        userService.updatePoints(1, 10, true);
        User updatedUser = userRepository.findById(user.getId()).orElse(null);

        // Assert
        assert updatedUser != null;
        assertEquals(30, updatedUser.getTotalPoints());
        assertEquals(20, updatedUser.getCurrentPoints());

    }

    /**
     * If a task has been marked not done (done==false), the tasks points should be retracted from the users totalPoints and
     * currentPoints.
     */
    @Test
    void update_valid_points_decrease() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setCurrentPoints(10);
        user.setTotalPoints(20);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        // Act
        userService.updatePoints(1, 10, false);
        User updatedUser = userRepository.findById(user.getId()).orElse(null);

        // Assert
        assert updatedUser != null;
        assertEquals(10, updatedUser.getTotalPoints());
        assertEquals(0, updatedUser.getCurrentPoints());

    }

}