package com.fredande.rewardsappbackend.mapper;

import com.fredande.rewardsappbackend.dto.TaskReadResponse;
import com.fredande.rewardsappbackend.dto.TaskSavedResponse;
import com.fredande.rewardsappbackend.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskSavedResponse taskToTaskSavedResponse(Task task);

    TaskReadResponse taskToTaskReadResponse(Task task);

}
