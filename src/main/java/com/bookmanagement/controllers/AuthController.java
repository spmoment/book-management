package com.bookmanagement.controllers;

import com.bookmanagement.config.jwt.JwtProvider;
import com.bookmanagement.dto.AuthRequest;
import com.bookmanagement.dto.AuthResponse;
import com.bookmanagement.entity.Users;
import com.bookmanagement.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class AuthController {

    private UserService userService;

    private JwtProvider jwtProvider;

    private static final Logger log = Logger.getLogger("AuthController.class");

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public Users registerUser(@RequestBody @Valid Users users) {
        log.info(users.toString());
        return userService.saveUser(users);
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Users users = userService.findByPhoneNumberAndPassword(request.getPhoneNumber(), request.getPassword());
        String token = jwtProvider.generateToken(users.getPhoneNumber());
        return new AuthResponse(token, users.getRole().name());
    }
}
