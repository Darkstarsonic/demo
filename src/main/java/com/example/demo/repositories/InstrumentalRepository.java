package com.example.demo.repositories;

import com.example.demo.model.Instrumental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentalRepository extends JpaRepository<Instrumental,Long> {
    public boolean existsByName(String name);

    public Instrumental findByName(String name);
}
