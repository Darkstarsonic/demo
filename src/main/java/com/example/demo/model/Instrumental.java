package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "instrumentals")
@NoArgsConstructor
public class Instrumental {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    private List<Subject> subjects;

    public Instrumental(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
    }
}
