package com.debraj.eventmanagement.service;

import com.debraj.eventmanagement.dto.LoginResponseDto;
import com.debraj.eventmanagement.dto.UserResponseDto;
import com.debraj.eventmanagement.entity.User;
import com.debraj.eventmanagement.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository repository,
                       BCryptPasswordEncoder passwordEncoder,JwtService jwtService) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }

    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repository.save(user);
    }
    public LoginResponseDto login(String email, String password) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new LoginResponseDto(token);
    }
    public User updateUser(Long id, User updatedUser) {

        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());

        return repository.save(user);
    }
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}