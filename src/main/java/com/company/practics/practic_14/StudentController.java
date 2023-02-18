package com.company.practics.practic_14;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>(){{
        add(new Student("Ivan", "Ivanov", "Ivanovich"));
        add(new Student("Maria", "Petrova", "Alexandrovna"));
        add(new Student("Alexey", "Sidorov", "Andreevich"));
        add(new Student("Anna", "Semenova", "Sergeevna"));
        add(new Student("Sergei", "Kozlov", "Dmitrievich"));
        add(new Student("Olga", "Kuznetsova", "Ivanovna"));
    }};


    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) {
        students.add(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        students.removeIf(s -> s.getId() == id);
    }

}