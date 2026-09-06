package com.fredrikkodar.chorely.dto;

import com.fredrikkodar.chorely.constants.ValidationConstants;
import com.fredrikkodar.chorely.enums.GroupRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record GroupInvitationRequest(
        @NotNull
        @Pattern(regexp = ValidationConstants.EMAIL_REGEX,
                message = "Malformed email address")
        String email,
        @NotNull
        Integer targetId,
        GroupRole groupRole
) implements InvitationRequest {

}
