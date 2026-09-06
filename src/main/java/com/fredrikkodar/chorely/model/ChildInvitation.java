package com.fredrikkodar.chorely.model;

import com.fredrikkodar.chorely.enums.PermissionLevel;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CHILD_SHARE")
public class ChildInvitation extends Invitation {

    private Integer childId;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    public ChildInvitation(String invitedEmail, Integer invitedById, String tokenHash, Integer childId, PermissionLevel permissionLevel) {
        super(invitedEmail, invitedById, tokenHash);
        this.childId = childId;
        this.permissionLevel = permissionLevel;
    }

    public ChildInvitation() {

    }

}
