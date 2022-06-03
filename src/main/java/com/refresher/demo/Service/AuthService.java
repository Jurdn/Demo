package com.refresher.demo.Service;

import com.refresher.demo.Entity.UserEntity;
import com.refresher.demo.Models.LoginCredentials;
import com.refresher.demo.Repository.UserRepository;
import com.refresher.demo.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    AuthenticationManager authManager;

    public ResponseEntity<Map<String, Object>> registerUser(UserEntity user) {
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("jwt-token", token));
    }

    public ResponseEntity<?> loginUser(LoginCredentials loginCredentials) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginCredentials.getUsername(), loginCredentials.getPassword());
            authManager.authenticate(authToken);
            String jwtToken = jwtUtil.generateToken(loginCredentials.getUsername());
            return ResponseEntity.ok(Collections.singletonMap("jwt-token", jwtToken));
        } catch (AuthenticationException err) {
            System.out.println(err);
            return ResponseEntity.status(401).body("Wrong username/password.");
        }
    }
}
