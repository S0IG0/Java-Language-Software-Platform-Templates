package com.company.practics.practic_18.services.impl;

import com.company.practics.practic_18.models.Student;
import com.company.practics.practic_18.models.University;
import com.company.practics.practic_18.repositories.UniversityRepository;
import com.company.practics.practic_18.services.UniversityService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {
    private UniversityRepository universityRepository;
    private EntityManager entityManager;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository, EntityManager entityManager) {
        this.universityRepository = universityRepository;
        this.entityManager = entityManager;
    }

    @Override
    public ResponseEntity<University> createUniversity(University university) {
        University savedUniversity = universityRepository.save(university);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUniversity);
    }

    @Override
    public List<University> getAll() {
        return (List<University>) universityRepository.findAll();
    }

    @Override
    public ResponseEntity<University> getById(Long id) {
        return universityRepository.findById(id)
                .map(university -> ResponseEntity.ok().body(university))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<University> updateUniversity(Long id, University university) {
        return universityRepository.findById(id)
                .map(existingUniversity -> {
                    existingUniversity.setName(university.getName());
                    existingUniversity.setCreationDate(university.getCreationDate());
                    University updatedUniversity = universityRepository.save(existingUniversity);
                    return ResponseEntity.ok().body(updatedUniversity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteUniversity(Long id) {
        return universityRepository.findById(id)
                .map(university -> {
                    universityRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public List<University> getUniversitiesByFiltered(String firstName, String lastName, String middleName, String universityName, LocalDate creationDate) {
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
