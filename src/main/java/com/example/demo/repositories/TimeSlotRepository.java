package com.example.demo.repositories;

import com.example.demo.model.Lesson;
import com.example.demo.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    public TimeSlot findByStartAt(Integer startAt);
}
