package com.example.demo.controllers;

import com.example.demo.model.Instrumental;
import com.example.demo.model.Subject;
import com.example.demo.repositories.InstrumentalRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.InstrumentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/instrument")
public class InstrumentController {
    final InstrumentalRepository instrumentalRepository;
    final RoleRepository roleRepository;

    final InstrumentalService instrumentalService;


    @Autowired
    public InstrumentController(InstrumentalRepository instrumentalRepository, RoleRepository roleRepository, InstrumentalService instrumentalService) {
        this.instrumentalRepository = instrumentalRepository;
        this.roleRepository = roleRepository;
        this.instrumentalService = instrumentalService;
    }

    @GetMapping("/getAllInstruments")
    public List<Instrumental> getAllInstrumental() {
        return instrumentalService.getAllInstrumental();
    }
}
