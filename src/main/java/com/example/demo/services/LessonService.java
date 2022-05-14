package com.example.demo.services;

import com.example.demo.DTO.CreateLessonRequest;
import com.example.demo.model.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class LessonService {

    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    private final SubjectRepository subjectRepository;
    private final InstrumentalRepository instrumentalRepository;

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public LessonService(UserRepository userRepository, LessonRepository lessonRepository, SubjectRepository subjectRepository, InstrumentalRepository instrumentalRepository, TimeSlotRepository timeSlotRepository) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.subjectRepository = subjectRepository;
        this.instrumentalRepository = instrumentalRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public void CreateLesson(CreateLessonRequest createLessonRequest) throws InstanceAlreadyExistsException{
        Integer startTime = Integer.parseInt(createLessonRequest.getTimeSlot().substring(0, 2));
        TimeSlot timeSlot = timeSlotRepository.findByStartAt(startTime);
        User student = userRepository.findById(createLessonRequest.getStudentId()).get();
        User teacher = userRepository.findById(createLessonRequest.getTeacherId()).get();
        if (student.isTimeSlotUnavailable(timeSlot)) {
            throw new InstanceAlreadyExistsException("Time is already taken by another subject");
        }
        Subject subject = subjectRepository.findById(createLessonRequest.getSubjectId()).get();
        Optional<Lesson> lesson = lessonRepository.findByTeacherAndSubjectAndTimeSlot(teacher, subject, timeSlot);
        if (lesson.isPresent()) {
            lesson.get().addStudent(student);
            lessonRepository.save(lesson.get());
        } else {
            if (lessonRepository.existsByTeacherAndTimeSlot(teacher, timeSlot)) {
                throw new InstanceAlreadyExistsException("Teacher's time is unavailable");
            }
            Lesson newLesson = new Lesson(teacher, subject, timeSlot);
            newLesson.addStudent(student);
            lessonRepository.save(newLesson);
        }
        student.getTimeSlots().add(timeSlot);
        userRepository.save(student);
        teacher.getTimeSlots().add(timeSlot);
        userRepository.save(teacher);
    }
}
