package com.pblgllgs.security.services.impl;
/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */

import com.pblgllgs.security.dto.JwtAuthenticationResponse;
import com.pblgllgs.security.dto.SignUpRequest;
import com.pblgllgs.security.dto.SigninRequest;
import com.pblgllgs.security.entities.User;
import com.pblgllgs.security.enums.Role;
import com.pblgllgs.security.repository.UserRepository;
import com.pblgllgs.security.services.AuthenticationService;
import com.pblgllgs.security.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public User signUp(SignUpRequest signUpRequest){
        User user = User.builder()
                .email(signUpRequest.email())
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .role(Role.USER)
                .password(passwordEncoder.encode(signUpRequest.password()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse login(SigninRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow( () -> new UsernameNotFoundException("USERNAME_NOT_FOUND"));
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.refreshToken(new HashMap<>(),user);
        return new JwtAuthenticationResponse(
                accessToken,
                refreshToken
        );
    }

}
