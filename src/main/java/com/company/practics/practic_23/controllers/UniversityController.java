package com.company.practics.practic_23.controllers;

import com.company.practics.practic_23.models.University;
import com.company.practics.practic_23.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    private UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<University> create(@RequestBody University university) {
        return universityService.createUniversity(university);
    }

    @GetMapping
    public List<University> getAll() {
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> read(@PathVariable Long id) {
        return universityService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, @RequestBody University university) {
        return universityService.updateUniversity(id, university);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return universityService.deleteUniversity(id);
    }

    @GetMapping("/filtered")
    public List<University> getFilteredStudents(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "universityName", required = false) String universityName,
            @RequestParam(name = "creationDate", required = false) LocalDate creationDate
    ) {
        return universityService.getUniversitiesByFiltered(firstName, lastName, middleName, universityName, creationDate);
    }
}
