package com.fredrikkodar.chorely.repository;

import com.fredrikkodar.chorely.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    boolean existsByIdAndOwner(Integer groupId, Integer owner);

}
