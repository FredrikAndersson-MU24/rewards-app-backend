package com.fredande.rewardsappbackend.mapper;

import com.fredande.rewardsappbackend.dto.UserResponse;
import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.model.User;
import com.fredande.rewardsappbackend.repository.UserRepository;
import com.fredande.rewardsappbackend.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "numTasksOpen", expression = "java(getNumOpenTasks(user))")
    @Mapping(target = "numTasksCompleted", expression = "java(getNumCompletedTasks(user))")
    @Mapping(target = "numTasksTotal", expression = "java(numTasksCompleted + numTasksOpen)")
    UserResponse userToUserResponse(User user);

    default Integer getNumOpenTasks(User user) {
        Integer numTasks = 0;
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (!task.isDone()) {
                numTasks++;
            }
        }
        return numTasks;
    }

    default Integer getNumCompletedTasks(User user) {
        Integer numTasks = 0;
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (task.isDone()) {
                numTasks++;
            }
        }
        return numTasks;
    }

}
