package com.example.demo.controllers;

import com.example.demo.model.Lesson;
import com.example.demo.model.Subject;
import com.example.demo.repositories.SubjectRepository;
import com.example.demo.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/subjects")
public class SubjectController {

    final
    SubjectRepository subjectRepository;

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }

    public List<Lesson> getListOfLessons(String subjectName) {
        List<Lesson> listLesson = new ArrayList<>();
        return listLesson;
    }
    @GetMapping("/getAllSubjects")
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }
}
