package com.example.demo.services;

import com.example.demo.DTO.CreateLessonRequest;
import com.example.demo.model.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    private final SubjectRepository subjectRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public LessonService(UserRepository userRepository, LessonRepository lessonRepository, SubjectRepository subjectRepository, TimeSlotRepository timeSlotRepository) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.subjectRepository = subjectRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createLesson(CreateLessonRequest createLessonRequest) throws InstanceAlreadyExistsException {
        Integer startTime = Integer.parseInt(createLessonRequest.getTimeSlot().substring(0, 2));
        TimeSlot timeSlot = timeSlotRepository.findByStartAt(startTime);
        User student = userRepository.findById(createLessonRequest.getStudentId()).get();
        User teacher = userRepository.findById(createLessonRequest.getTeacherId()).get();
        if (student.isTimeSlotUnavailable(timeSlot)) {
            throw new InstanceAlreadyExistsException("Время занято другим предметом");
        }
        Subject subject = subjectRepository.findById(createLessonRequest.getSubjectId()).get();
        Optional<Lesson> lesson = lessonRepository.findByTeacherAndSubjectAndTimeSlot(teacher, subject, timeSlot);
        if (lesson.isPresent()) {
            lesson.get().setUsers(new ArrayList<>() {{
                add(student);
            }});
            lessonRepository.save(lesson.get());
        } else {
            if (lessonRepository.existsByTeacherAndTimeSlot(teacher, timeSlot)) {
                throw new InstanceAlreadyExistsException("Время учителя занято");
            }
            Lesson newLesson = new Lesson();
            newLesson.setUsers(new ArrayList<>() {{
                add(student);
            }});
            newLesson.setSubject(subject);
            newLesson.setTimeSlot(timeSlot);
            newLesson.setTeacher(teacher);
            lessonRepository.save(newLesson);
        }
        student.getTimeSlots().add(timeSlot);
        userRepository.save(student);
        teacher.getTimeSlots().add(timeSlot);
        userRepository.save(teacher);
    }

    public List<Lesson> getLessonsStudent(Long studentId) {
        Optional<User> user = userRepository.findById(studentId);

        return user.map(value -> lessonRepository.findLessonsByUsersIn(new ArrayList<>() {{
            add(value);
        }})).orElse(null);
    }
}
