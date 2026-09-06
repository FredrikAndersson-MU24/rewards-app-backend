package com.fredrikkodar.chorely.model;

import com.fredrikkodar.chorely.enums.GroupRole;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "group_id"}))
public class GroupMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    private GroupRole role;

    private Instant joinedAt;

    private Integer invitedById;

    public GroupMembership() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupRole getRole() {
        return role;
    }

    public void setRole(GroupRole role) {
        this.role = role;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Integer getInvitedById() {
        return invitedById;
    }

    public void setInvitedById(Integer invitedById) {
        this.invitedById = invitedById;
    }

}
