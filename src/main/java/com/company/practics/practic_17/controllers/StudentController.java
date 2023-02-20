package com.company.practics.practic_17.controllers;

import com.company.practics.practic_17.models.Student;
import com.company.practics.practic_17.models.University;
import com.company.practics.practic_17.repositories.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("")
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> read(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setMiddleName(student.getMiddleName());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok().body(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtered")
    public List<Student> getFilteredStudents(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "universityName", required = false) String universityName,
            @RequestParam(name = "creationDate", required = false) LocalDate creationDate
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        Root<Student> root = criteria.from(Student.class);

        if (firstName != null) {
            criteria.where(builder.like(root.get("firstName"), "%" + firstName + "%"));
        }
        if (lastName != null) {
            criteria.where(builder.like(root.get("lastName"), "%" + lastName + "%"));
        }
        if (middleName != null) {
            criteria.where(builder.like(root.get("middleName"), "%" + middleName + "%"));
        }
        if (universityName != null || creationDate != null) {
            Join<Student, University> universityJoin = root.join("university");
            if (universityName != null) {
                criteria.where(builder.like(universityJoin.get("name"), "%" + universityName + "%"));
            }
            if (creationDate != null) {
                criteria.where(builder.equal(universityJoin.get("creationDate"), creationDate));
            }
        }

        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }

}
