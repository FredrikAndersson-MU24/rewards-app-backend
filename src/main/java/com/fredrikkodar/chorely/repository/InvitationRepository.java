package com.fredrikkodar.chorely.repository;

import com.fredrikkodar.chorely.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {

}
