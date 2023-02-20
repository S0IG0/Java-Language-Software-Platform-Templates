package com.company.practics.practic_21.services;

import com.company.practics.practic_21.models.Student;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    ResponseEntity<Student> createStudent(Student student);
    List<Student> getAll();
    ResponseEntity<Student> getById(Long id);
    ResponseEntity<Student> updateStudent(Long id, Student student);
    ResponseEntity<?> deleteStudent(Long id);
    List<Student> getStudentsByFiltered(
            String firstName,
            String lastName,
            String middleName,
            String universityName,
            LocalDate creationDate
    );
}
