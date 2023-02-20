package com.company.practics.practic_17.controllers;

import com.company.practics.practic_17.models.Student;
import com.company.practics.practic_17.models.University;
import com.company.practics.practic_17.repositories.UniversityRepository;
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
@RequestMapping("/universities")
public class UniversityController {
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<University> create(@RequestBody University university) {
        University savedUniversity = universityRepository.save(university);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUniversity);
    }

    @GetMapping
    public List<University> getAll() {
        return (List<University>) universityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> read(@PathVariable Long id) {
        return universityRepository.findById(id)
                .map(university -> ResponseEntity.ok().body(university))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, @RequestBody University university) {
        return universityRepository.findById(id)
                .map(existingUniversity -> {
                    existingUniversity.setName(university.getName());
                    existingUniversity.setCreationDate(university.getCreationDate());
                    University updatedUniversity = universityRepository.save(existingUniversity);
                    return ResponseEntity.ok().body(updatedUniversity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return universityRepository.findById(id)
                .map(university -> {
                    universityRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtered")
    public List<University> getFilteredStudents(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "universityName", required = false) String universityName,
            @RequestParam(name = "creationDate", required = false) LocalDate creationDate
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<University> criteria = builder.createQuery(University.class);
        Root<University> root = criteria.from(University.class);

        if (universityName != null) {
            criteria.where(builder.like(root.get("name"), "%" + universityName + "%"));
        }
        if (creationDate != null) {
            criteria.where(builder.like(root.get("creationDate"), "%" + creationDate + "%"));
        }
        if (firstName != null || lastName != null || middleName != null) {
            Join<University, Student> universityJoin = root.join("students");
            if (firstName != null) {
                criteria.where(builder.like(universityJoin.get("firstName"), "%" + firstName + "%"));
            }
            if (lastName != null) {
                criteria.where(builder.like(universityJoin.get("lastName"), "%" + lastName + "%"));
            }
            if (middleName != null) {
                criteria.where(builder.like(universityJoin.get("middleName"), "%" + middleName + "%"));
            }
        }

        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }
}
