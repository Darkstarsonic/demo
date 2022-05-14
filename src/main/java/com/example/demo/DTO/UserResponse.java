package com.example.demo.DTO;

import com.example.demo.model.Instrumental;
import com.example.demo.model.Lesson;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {

    private Long id;

    private String email;

    private String fullName;

    private Role role;

    private List<Lesson> lessons;

    private Instrumental instrumental;

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.role = user.getRole();
        this.lessons = user.getLessons();
        this.instrumental = user.getInstrumental();
    }

}
