package com.pblgllgs.security.controller;
/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */

import com.pblgllgs.security.dto.SignUpRequest;
import com.pblgllgs.security.dto.SigninRequest;
import com.pblgllgs.security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpRequest signUpRequest){
        return new ResponseEntity<>(authenticationService.signUp(signUpRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> register(@RequestBody SigninRequest signinRequest){
        return new ResponseEntity<>(authenticationService.login(signinRequest), HttpStatus.OK);
    }
}
