package com.fredande.rewardsappbackend.repository;

import com.fredande.rewardsappbackend.model.Task;
import com.fredande.rewardsappbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(User user);

}
