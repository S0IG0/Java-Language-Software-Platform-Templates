package com.company.practics.practic_19.services.impl;

import com.company.practics.practic_19.models.Student;
import com.company.practics.practic_19.models.University;
import com.company.practics.practic_19.repositories.StudentRepository;
import com.company.practics.practic_19.services.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private EntityManager entityManager;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    @Override
    public ResponseEntity<Student> createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        log.info("Created student with id " + savedStudent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @Override
    public List<Student> getAll() {
        log.info("Retrieving all students");
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public ResponseEntity<Student> getById(Long id) {
        log.info("Retrieving student with id " + id);
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Student> updateStudent(Long id, Student student) {
        log.info("Updating student with id " + id);
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

    @Override
    public ResponseEntity<?> deleteStudent(Long id) {
        log.info("Deleting student with id " + id);
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public List<Student> getStudentsByFiltered(
            String firstName,
            String lastName,
            String middleName,
            String universityName,
            LocalDate creationDate
    ) {
        log.info(
                "Retrieving students with filters - firstName: " + firstName +
                        ", lastName: " + lastName +
                        ", middleName: " + middleName +
                        ", universityName: " + universityName +
                        ", creationDate: " + creationDate
        );
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
