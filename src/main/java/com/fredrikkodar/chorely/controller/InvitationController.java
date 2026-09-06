package com.fredrikkodar.chorely.controller;

import com.fredrikkodar.chorely.config.CustomUserDetails;
import com.fredrikkodar.chorely.dto.InvitationRequest;
import com.fredrikkodar.chorely.service.InvitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invitation")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    // Create
    @PostMapping
    public ResponseEntity<?> createInvitation(@AuthenticationPrincipal CustomUserDetails userDetails,
                                              InvitationRequest request) {
        invitationService.createInvitation(userDetails, request);
        return ResponseEntity.status(200).build();
    }


}
