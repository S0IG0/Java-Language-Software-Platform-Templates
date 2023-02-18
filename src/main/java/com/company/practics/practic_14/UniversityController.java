package com.company.practics.practic_14;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UniversityController {

    private final List<University> universities = new ArrayList<>(){{
        add(new University("University 1", LocalDate.of(2020, 1, 1)));
        add(new University("University 2", LocalDate.of(2020, 1, 2)));
        add(new University("University 3", LocalDate.of(2020, 1, 3)));
        add(new University("University 4", LocalDate.of(2020, 1, 4)));
        add(new University("University 5", LocalDate.of(2020, 1, 5)));
        add(new University("University 6", LocalDate.of(2020, 1, 6)));

    }};

    @PostMapping("/universities")
    public void createUniversity(@RequestBody University university) {
        universities.add(university);
    }

    @GetMapping("/universities")
    public List<University> getAllUniversities() {
        return universities;
    }

    @DeleteMapping("/universities/{id}")
    public void deleteUniversity(@PathVariable int id) {
        universities.removeIf(u -> u.getId() == id);
    }

}