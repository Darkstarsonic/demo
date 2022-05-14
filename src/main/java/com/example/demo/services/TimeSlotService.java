package com.example.demo.services;

import com.example.demo.model.TimeSlot;
import com.example.demo.repositories.TimeSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getAllTimeSlot() {
        List<TimeSlot> timeSlots =timeSlotRepository.findAll();
        log.info(timeSlots.toString());
        return timeSlots;
    }
}
