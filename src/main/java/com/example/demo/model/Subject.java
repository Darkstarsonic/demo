package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subjects")
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
