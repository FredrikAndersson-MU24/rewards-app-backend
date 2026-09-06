package com.fredrikkodar.chorely.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChildShareInvitationRequest.class, name = "CHILD_SHARE"),
        @JsonSubTypes.Type(value = GroupInvitationRequest.class, name = "GROUP")
})
public sealed interface InvitationRequest permits GroupInvitationRequest, ChildShareInvitationRequest {

    String email();

    Integer targetId();

}
