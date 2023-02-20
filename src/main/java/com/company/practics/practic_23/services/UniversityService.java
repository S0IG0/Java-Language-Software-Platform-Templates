package com.company.practics.practic_23.services;

import com.company.practics.practic_23.models.University;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface UniversityService {
    ResponseEntity<University> createUniversity(University university);
    List<University> getAll();
    ResponseEntity<University> getById(Long id);
    ResponseEntity<University> updateUniversity(Long id, University university);
    ResponseEntity<?> deleteUniversity(Long id);
    List<University> getUniversitiesByFiltered(
            String firstName,
            String lastName,
            String middleName,
            String universityName,
            LocalDate creationDate
    );
}
