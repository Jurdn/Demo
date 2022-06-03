package com.refresher.demo.Controller;

import com.refresher.demo.Entity.UserEntity;
import com.refresher.demo.Models.LoginCredentials;
import com.refresher.demo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserEntity user) {
        return authService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginCredentials loginCredentials) {
        System.out.println(loginCredentials);
        return authService.loginUser(loginCredentials);
    }

}
