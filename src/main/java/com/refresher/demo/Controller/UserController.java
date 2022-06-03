package com.refresher.demo.Controller;

import com.refresher.demo.Entity.UserEntity;
import com.refresher.demo.Repository.UserRepository;
import com.refresher.demo.Service.MyUserDetailsService;
import com.refresher.demo.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping(value = "test")
    public ResponseEntity<?> getInfos() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
        assert userOpt.isPresent();
        return ResponseEntity.ok(userOpt.get());
    }
}
