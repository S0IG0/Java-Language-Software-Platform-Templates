package com.company.practics.practic_21.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "university")
@Getter
@Setter
public class University {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "university")
    private List<Student> students;
}