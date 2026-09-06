package com.fredrikkodar.chorely.repository;

import com.fredrikkodar.chorely.model.ParentChildAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentChildAccessRepository extends JpaRepository<ParentChildAccess, Integer> {

}
