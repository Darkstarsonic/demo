package com.example.demo.DTO;

import com.example.demo.model.Subject;
import com.example.demo.model.TimeSlot;
import com.example.demo.model.User;
import lombok.Data;

import java.util.List;

@Data
public class LessonResponse {
    private Long id;

    private List<User> students;

    private User teacher;

    private Subject subject;

    private TimeSlot timeSlot;

    public LessonResponse(Long id, List<User> students, User teacher, Subject subject, TimeSlot timeSlot) {
        this.id = id;
        this.students = students;
        this.teacher = teacher;
        this.subject = subject;
        this.timeSlot = timeSlot;
    }
}
