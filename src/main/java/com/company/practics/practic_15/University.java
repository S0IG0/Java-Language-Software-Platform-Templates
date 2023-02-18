package com.company.practics.practic_15;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
}