package com.company.practics.practic_21.services.impl;

import com.company.practics.practic_21.models.Student;
import com.company.practics.practic_21.models.University;
import com.company.practics.practic_21.repositories.StudentRepository;
import com.company.practics.practic_21.services.EmailService;
import com.company.practics.practic_21.services.StudentService;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private final EmailService emailService;
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    @Autowired
    public StudentServiceImpl(
            StudentRepository studentRepository,
            EntityManager entityManager,
            EmailService emailService
    ) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
        this.emailService = emailService;
    }

    @Transactional
    @Override
    public ResponseEntity<Student> createStudent(Student student) {
        emailService.sendEmailToYourSelf(
                "createStudent",
                student.toString()
        );
        Student savedStudent = studentRepository.save(student);
        log.info("Created student with id " + savedStudent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAll() {
        log.info("Retrieving all Students");
        List<Student> students = studentRepository.findAll();
        emailService.sendEmailToYourSelf(
                "get all students",
                students.stream()
                        .map(Student::getFirstName)
                        .collect(Collectors.joining("\n"))
        );
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Student> getById(Long id) {
        log.info("Retrieving student with id " + id);
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
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

    @Transactional
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

    @Transactional(readOnly = true)
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
