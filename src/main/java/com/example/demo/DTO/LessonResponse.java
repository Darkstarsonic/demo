package com.example.demo.DTO;

import com.example.demo.model.Lesson;
import com.example.demo.model.Subject;
import com.example.demo.model.TimeSlot;
import com.example.demo.model.User;
import lombok.Data;

import java.util.List;

@Data
public class LessonResponse {
    private Lesson lesson;

}
