package com.fredande.rewardsappbackend.service;

import com.fredande.rewardsappbackend.dto.LoginRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationServiceDef {

    UserDetails authenticate(String username, String password);

    String generateToken(UserDetails userDetails);

    //    UserDetails validateToken(String token);
    void register(LoginRequest user);

}
