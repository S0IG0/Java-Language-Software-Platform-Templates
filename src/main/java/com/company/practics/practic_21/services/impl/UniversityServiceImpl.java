package com.company.practics.practic_21.services.impl;

import com.company.practics.practic_21.models.Student;
import com.company.practics.practic_21.models.University;
import com.company.practics.practic_21.repositories.UniversityRepository;
import com.company.practics.practic_21.services.EmailService;
import com.company.practics.practic_21.services.UniversityService;
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
public class UniversityServiceImpl implements UniversityService {
    private final EmailService emailService;
    private final UniversityRepository universityRepository;
    private final EntityManager entityManager;

    @Autowired
    public UniversityServiceImpl(
            UniversityRepository universityRepository,
            EntityManager entityManager,
            EmailService emailService
    ) {
        this.universityRepository = universityRepository;
        this.entityManager = entityManager;
        this.emailService = emailService;
    }
    @Transactional
    @Override
    public ResponseEntity<University> createUniversity(University university) {
        emailService.sendEmailToYourSelf(
                "createUniversity",
                university.toString()
        );
        University savedUniversity = universityRepository.save(university);
        log.info("Created University with id " + savedUniversity.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUniversity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<University> getAll() {
        log.info("Retrieving all Universities");
        List<University> universities =  universityRepository.findAll();
        emailService.sendEmailToYourSelf(
                "get all universities",
                universities.stream()
                        .map(University::getName)
                        .collect(Collectors.joining("\n"))
        );
        return universities;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<University> getById(Long id) {
        log.info("Retrieving University with id " + id);
        return universityRepository.findById(id)
                .map(university -> ResponseEntity.ok().body(university))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<University> updateUniversity(Long id, University university) {
        log.info("Updating University with id " + id);
        return universityRepository.findById(id)
                .map(existingUniversity -> {
                    existingUniversity.setName(university.getName());
                    existingUniversity.setCreationDate(university.getCreationDate());
                    University updatedUniversity = universityRepository.save(existingUniversity);
                    return ResponseEntity.ok().body(updatedUniversity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteUniversity(Long id) {
        log.info("Deleting University with id " + id);
        return universityRepository.findById(id)
                .map(university -> {
                    universityRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @Transactional(readOnly = true)
    @Override
    public List<University> getUniversitiesByFiltered(
            String firstName,
            String lastName,
            String middleName,
            String universityName,
            LocalDate creationDate
    ) {
        log.info(
                "Retrieving Universities with filters - firstName: " + firstName +
                        ", lastName: " + lastName +
                        ", middleName: " + middleName +
                        ", universityName: " + universityName +
                        ", creationDate: " + creationDate
        );
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
