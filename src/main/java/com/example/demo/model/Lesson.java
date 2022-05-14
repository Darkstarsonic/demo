package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lessons")
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<User> students;

    @ManyToOne
    private User teacher;

    @OneToOne
    private Subject subject;

    @ManyToOne
    private TimeSlot timeSlot;

    public Lesson(User teacher, Subject subject, TimeSlot timeSlot) {
        this.teacher = teacher;
        this.students = new ArrayList<>();
        this.subject = subject;
        this.timeSlot = timeSlot;
    }

    public void addStudent(User student) {
        this.students.add(student);
    }
}
