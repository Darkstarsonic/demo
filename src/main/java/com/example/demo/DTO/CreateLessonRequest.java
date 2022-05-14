package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLessonRequest {
    private Long teacherId;
    private Long subjectId;
    private String timeSlot;
    private Long studentId;
}
