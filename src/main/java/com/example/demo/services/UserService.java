package com.example.demo.services;

import com.example.demo.DTO.JWTRequest;
import com.example.demo.DTO.RegistrationRequest;
import com.example.demo.DTO.UserResponse;
import com.example.demo.model.Instrumental;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repositories.*;
import com.example.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final InstrumentalRepository instrumentalRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, InstrumentalRepository instrumentalRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.instrumentalRepository = instrumentalRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @org.springframework.transaction.annotation.Transactional
    public void createStudent(String email, String password, String fullName, Long instrumentId) {
        User student = new User();
        student.setInstrumental(instrumentalRepository.getById(instrumentId));
        student.setEmail(email);
        student.setFullName(fullName);
        student.setPassword(passwordEncoder.encode(password));
        student.setRole(roleRepository.getById(3L));

        userRepository.save(student);
    }

    @org.springframework.transaction.annotation.Transactional
    public void createTeacher(RegistrationRequest registrationRequest) {
        User teacher = new User();
        teacher.setInstrumental(instrumentalRepository.getById(registrationRequest.getInstrument()));
        teacher.setEmail(registrationRequest.getEmail());
        teacher.setFullName(registrationRequest.getFullName());
        teacher.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        teacher.setRole(roleRepository.getById(2L));

        userRepository.save(teacher);
    }

    public UserResponse getUserProfile(JWTRequest jwtRequest) throws UsernameNotFoundException {
        String email = jwtUtil.extractUsername(jwtRequest.getJwt());
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User was not found");
        }
        return new UserResponse(user.get());
    }

    public List<UserResponse> getByRoleIdAndInstrumentId(Long roleId, Long instrumentId) {
        Role role = roleRepository.findById(roleId).get();
        Instrumental instrumental = instrumentalRepository.findById(instrumentId).get();
        List<User> users = userRepository.findAllByInstrumentalAndRole(instrumental, role);
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
