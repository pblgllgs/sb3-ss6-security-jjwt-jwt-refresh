package com.pblgllgs.security.controller;
/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> user(){
        return new ResponseEntity<>("USER", HttpStatus.OK);
    }

}
