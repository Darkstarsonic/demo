package com.example.demo.controllers;

import com.example.demo.DTO.JWTRequest;
import com.example.demo.DTO.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repositories.*;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/main")
public class UserController {

    final UserRepository userRepository;
    final InstrumentalRepository instrumentalRepository;
    final LessonRepository lessonRepository;

    final RoleRepository roleRepository;

    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,
                          InstrumentalRepository instrumentalRepository,
                          LessonRepository lessonRepository, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.instrumentalRepository = instrumentalRepository;
        this.lessonRepository = lessonRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostMapping("/getUserProfile")
    public ResponseEntity<?> getUserProfile(@RequestBody JWTRequest jwtRequest) {
        UserResponse userResponse;
        try {
            userResponse = userService.getUserProfile(jwtRequest);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/getTeachers")
    public List<UserResponse> getTeachers(@RequestParam Long instrumentId) {
        return userService.getByRoleIdAndInstrumentId(2L, instrumentId);
    }

}
