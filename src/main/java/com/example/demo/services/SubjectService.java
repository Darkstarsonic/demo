package com.example.demo.services;

import com.example.demo.model.Lesson;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public boolean addSubject(@RequestParam String name,
                              @RequestParam String description) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setDescription(description);

        subjectRepository.save(subject);
        return true;
    }

    public Subject getSubjectById() {
        return null;
    }

    public List<Lesson> getListOfLessons(String subjectName) {
        List<Lesson> lessonList = new ArrayList<>();
        return lessonList;
    }

    public boolean addStudentToSubject(String subjectName, User student) {
        return false;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
