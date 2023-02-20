package com.company.practics.practic_19.controllers;

import com.company.practics.practic_19.models.Student;
import com.company.practics.practic_19.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("")
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> read(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/filtered")
    public List<Student> getFilteredStudents(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "universityName", required = false) String universityName,
            @RequestParam(name = "creationDate", required = false) LocalDate creationDate
    ) {
        return studentService.getStudentsByFiltered(firstName, lastName, middleName, universityName, creationDate);
    }

}
