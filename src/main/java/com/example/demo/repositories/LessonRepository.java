package com.example.demo.repositories;

import com.example.demo.model.Lesson;
import com.example.demo.model.Subject;
import com.example.demo.model.TimeSlot;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    //List<Lesson> getLessonsByStudent (User student);
    public Optional<Lesson> findByTeacherAndSubjectAndTimeSlot(User teacher, Subject subject, TimeSlot timeSlot);

    boolean existsByTeacherAndTimeSlot(User teacher, TimeSlot timeSlot);
}
