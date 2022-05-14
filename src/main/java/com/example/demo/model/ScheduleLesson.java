package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "scheduleLesson")
@NoArgsConstructor
public class ScheduleLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Lesson lessonId;

    @OneToOne
    private Schedule scheduleId;
}
