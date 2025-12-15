package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.dto.RegistrationRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationServiceDef {

    UserDetails authenticate(String username, String password);

    String generateToken(UserDetails userDetails);

    void register(RegistrationRequest registrationRequest);

}
