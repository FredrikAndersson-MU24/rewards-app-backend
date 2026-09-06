package com.fredrikkodar.chorely.service;

import com.fredrikkodar.chorely.config.CustomUserDetails;
import com.fredrikkodar.chorely.dto.ChildShareInvitationRequest;
import com.fredrikkodar.chorely.dto.GroupInvitationRequest;
import com.fredrikkodar.chorely.dto.InvitationRequest;
import com.fredrikkodar.chorely.enums.GroupRole;
import com.fredrikkodar.chorely.enums.PermissionLevel;
import com.fredrikkodar.chorely.model.ChildInvitation;
import com.fredrikkodar.chorely.model.GroupInvitation;
import com.fredrikkodar.chorely.model.Invitation;
import com.fredrikkodar.chorely.repository.InvitationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class InvitationService {

    @Value("${invitation.salt}")
    private String invitationSalt;

    private static final Logger LOGGER = LoggerFactory.getLogger(InvitationService.class);

    private final InvitationRepository invitationRepository;
    private final GroupService groupService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final ApplicationEventPublisher eventPublisher;

    public InvitationService(InvitationRepository invitationRepository, GroupService groupService, UserService userService, ApplicationEventPublisher eventPublisher) {
        this.invitationRepository = invitationRepository;
        this.groupService = groupService;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void createInvitation(CustomUserDetails userDetails, InvitationRequest request) {

        Invitation invitation = switch (request) {
            case GroupInvitationRequest r -> createGroupInvitation(userDetails, r.email(), r.groupRole(), r.targetId());
            case ChildShareInvitationRequest r ->
                    createChildShareInvitation(userDetails, r.email(), r.permissionLevel(), r.targetId());
        };

        // Generate token
        UUID token = UUID.randomUUID();

        // Hash token
        String tokenHash = generateTokenHash(token.toString());

        invitation.setTokenHash(tokenHash);

        // Construct link
        String link = "http://localhost:8080/api/invitation/" + token;

        // Save invitation and Send email. Transaction?


        LOGGER.info("Invitation email sent to: {} with link: {}", invitation.getInvitedEmail(), link);
    }

    private Invitation createChildShareInvitation(CustomUserDetails userDetails, String email, PermissionLevel permissionLevel, Integer targetId) {
        if (!userService.isParentOfUserId(targetId, userDetails.getId()))
            throw new IllegalArgumentException("User is not parent of child");
        return new ChildInvitation(email, userDetails.getId(), null, targetId, permissionLevel);
    }

    private Invitation createGroupInvitation(CustomUserDetails userDetails, String email, GroupRole groupRole, Integer targetId) {
        if (!groupService.isOwnerOfId(targetId, userDetails.getId()))
            throw new IllegalArgumentException("User is not owner of group");
        return new GroupInvitation(email, userDetails.getId(), null, targetId, groupRole);
    }

    public String generateTokenHash(String token) {
        return passwordEncoder.encode(token + invitationSalt);
    }

}
