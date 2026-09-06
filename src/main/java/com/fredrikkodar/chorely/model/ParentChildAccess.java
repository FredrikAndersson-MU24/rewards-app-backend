package com.fredrikkodar.chorely.model;

import com.fredrikkodar.chorely.enums.PermissionLevel;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "parent_child_access",
        uniqueConstraints = @UniqueConstraint(columnNames = {"parent_id", "child_id"}))
public class ParentChildAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private User child;

    private PermissionLevel permissionLevel;

    private Integer grantedAccessBy;

    private Instant grantedAt;

    public User getChild() {
        return child;
    }

    public void setChild(User child) {
        this.child = child;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

}
