package com.debraj.eventmanagement.controller;

import com.debraj.eventmanagement.dto.LoginRequest;
import com.debraj.eventmanagement.dto.LoginResponseDto;
import com.debraj.eventmanagement.dto.UserResponseDto;
import com.debraj.eventmanagement.entity.User;
import com.debraj.eventmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequest request) {

        return service.login(
                request.getEmail(),
                request.getPassword()
        );
    }
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        return service.updateUser(id, user);
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {

        service.deleteUser(id);

        return "User Deleted Successfully";
    }
}