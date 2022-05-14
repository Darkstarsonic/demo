package com.example.demo.controllers;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RegistrationRequest;
import com.example.demo.model.Instrumental;
import com.example.demo.repositories.InstrumentalRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.services.UserService;
import com.example.demo.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/authorization")
@Slf4j
public class AuthorizationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InstrumentalRepository instrumentalRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> proceedRegistration(@RequestBody RegistrationRequest registrationRequest) {
        log.info("Registration");
        if (userRepository.existsByEmail(registrationRequest.getEmail()) || !instrumentalRepository.existsByName(registrationRequest.getInstrument())) {
            return ResponseEntity.badRequest().body("User already exists or no such instrument.");
        }
        Instrumental instrumental = instrumentalRepository.findByName(registrationRequest.getInstrument());
        if(registrationRequest.getRole() == 3L)
            userService.createStudent(registrationRequest.getEmail(), registrationRequest.getPassword(), registrationRequest.getFullName(), instrumental.getId());
        else if(registrationRequest.getRole() == 2L)
            userService.createTeacher(registrationRequest);
        return ResponseEntity.ok("Success.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body("Incorrect username or password.");
        }

        final UserDetails userDetails = userDetailsService.
                loadUserByUsername(loginRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}
