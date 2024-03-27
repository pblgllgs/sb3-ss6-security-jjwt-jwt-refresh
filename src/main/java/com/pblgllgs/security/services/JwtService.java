package com.pblgllgs.security.services;

/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String refreshToken(Map<String, Object> objectObjectHashMap, UserDetails userDetails);
}
