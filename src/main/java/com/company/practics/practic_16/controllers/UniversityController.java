package com.company.practics.practic_16.controllers;

import com.company.practics.practic_16.models.University;
import com.company.practics.practic_16.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    @Autowired
    private UniversityRepository universityRepository;

    @PostMapping
    public ResponseEntity<University> create(@RequestBody University university) {
        University savedUniversity = universityRepository.save(university);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUniversity);
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

    @GetMapping
    public List<University> getAll() {
        return (List<University>) universityRepository.findAll();
    }
}
