package com.fredrikkodar.chorely.model;

import com.fredrikkodar.chorely.enums.GroupRole;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("GROUP")
public class GroupInvitation extends Invitation {

    private Integer childId;

    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;


    public GroupInvitation(String invitedEmail, Integer invitedById, String tokenHash, Integer childId, GroupRole groupRole) {
        super(invitedEmail, invitedById, tokenHash);
        this.childId = childId;
        this.groupRole = groupRole;
    }

    public GroupInvitation() {

    }

}
