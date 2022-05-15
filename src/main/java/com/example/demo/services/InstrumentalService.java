package com.example.demo.services;

import com.example.demo.model.Instrumental;
import com.example.demo.model.Subject;
import com.example.demo.repositories.InstrumentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentalService {
    final InstrumentalRepository instrumentalRepository;

    @Autowired
    public InstrumentalService(InstrumentalRepository instrumentalRepository) {
        this.instrumentalRepository = instrumentalRepository;
    }

    public List<Instrumental> getAllInstrumental() {
        return instrumentalRepository.findAll();
    }
}
