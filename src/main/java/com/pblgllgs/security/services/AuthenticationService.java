package com.pblgllgs.security.services;

import com.pblgllgs.security.dto.JwtAuthenticationResponse;
import com.pblgllgs.security.dto.SignUpRequest;
import com.pblgllgs.security.dto.SigninRequest;
import com.pblgllgs.security.entities.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse login(SigninRequest request);
}
