package com.example.demo.controllers;

import com.example.demo.DTO.CreateLessonRequest;
import com.example.demo.DTO.LessonResponse;
import com.example.demo.model.Lesson;
import com.example.demo.model.TimeSlot;
import com.example.demo.repositories.*;
import com.example.demo.services.LessonService;
import com.example.demo.services.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    private final TimeSlotService timeSlotService;

    @Autowired
    public LessonController(LessonService lessonService, TimeSlotService timeSlotService) {
        this.lessonService = lessonService;
        this.timeSlotService = timeSlotService;
    }

    @PostMapping(path = "/createLesson")
    public ResponseEntity<?> CreateLesson(@RequestBody CreateLessonRequest createLessonRequest) {
        try {
            lessonService.createLesson(createLessonRequest);
        } catch (InstanceAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Created lesson.");
    }

    @GetMapping("/getAllTimeSlot")
    public List<TimeSlot> getAllTimeSlot() {
        return timeSlotService.getAllTimeSlot();
    }

    @GetMapping("/schedule/{id}")
    public List<LessonResponse> getLessonStudent(@PathVariable(name = "id") Long studentId) {
        return lessonService.getLessonsStudent(studentId);
    }
}
