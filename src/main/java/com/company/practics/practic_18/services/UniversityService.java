package com.company.practics.practic_18.services;

import com.company.practics.practic_18.models.University;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface UniversityService {
    ResponseEntity<University> createUniversity(University university);
    List<University> getAll();
    ResponseEntity<University> getById(Long id);
    ResponseEntity<University> updateStudent(Long id, University university);
    ResponseEntity<?> deleteStudent(Long id);
    List<University> getUniversitiesByFiltered(
            String firstName,
            String lastName,
            String middleName,
            String universityName,
            LocalDate creationDate
    );
}
