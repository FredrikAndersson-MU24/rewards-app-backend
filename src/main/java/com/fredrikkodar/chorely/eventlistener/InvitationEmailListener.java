package com.fredrikkodar.chorely.eventlistener;

import com.fredrikkodar.chorely.repository.InvitationRepository;
import com.fredrikkodar.chorely.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class InvitationEmailListener {

    private final EmailService emailService;
    private final InvitationRepository invitationRepository;

    public InvitationEmailListener(EmailService emailService, InvitationRepository invitationRepository) {
        this.emailService = emailService;
        this.invitationRepository = invitationRepository;
    }

//    public void onInvitationEmail(InvitationCreatedEvent event) {
//
//    }

}
