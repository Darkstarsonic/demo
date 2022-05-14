package com.example.demo.controllers;

import com.example.demo.repositories.InstrumentalRepository;
import com.example.demo.repositories.RoleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/instrument")
public class InstrumentController {
    final InstrumentalRepository instrumentalRepository;
    final RoleRepository roleRepository;


    public InstrumentController(InstrumentalRepository instrumentalRepository, RoleRepository roleRepository) {
        this.instrumentalRepository = instrumentalRepository;
        this.roleRepository = roleRepository;
    }
}
