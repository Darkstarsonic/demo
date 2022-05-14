package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String fullName;

    @ManyToOne
    private Role role;

    @ManyToMany
    private List<Lesson> lessons;

    @ManyToOne
    private Instrumental instrumental;
    
    @ManyToMany
    private List<TimeSlot> timeSlots;

    public User(String email, String password, String fullName, Role role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.lessons = new ArrayList<>();
        this.timeSlots = new ArrayList<>();
    }

    public User(String email, String password, String fullName, Role role, Instrumental instrumental) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.lessons = new ArrayList<>();
        this.timeSlots = new ArrayList<>();
        this.instrumental = instrumental;
    }
    
    public boolean isTimeSlotUnavailable(TimeSlot timeSlot) {
        return this.timeSlots.contains(timeSlot);
    }
}
