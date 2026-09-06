package com.fredrikkodar.chorely.service;

import com.fredrikkodar.chorely.model.Group;
import com.fredrikkodar.chorely.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public boolean isOwnerOfId(Integer groupId, Integer userId) {
        return groupRepository.existsByIdAndOwner(groupId, userId);
    }

}
