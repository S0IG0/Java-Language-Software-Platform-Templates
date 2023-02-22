package com.company.practics.practic_24;

import com.company.practics.practic_22.models.University;
import com.company.practics.practic_22.repositories.UniversityRepository;
import com.company.practics.practic_22.services.EmailService;
import com.company.practics.practic_22.services.impl.UniversityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UniversityServiceImplTest {

    private UniversityServiceImpl universityService;

    @Mock
    private UniversityRepository universityRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        universityService = new UniversityServiceImpl(universityRepository, null, emailService);
    }

    @Test
    void createUniversity_shouldCreateUniversity() {
        University university = new University();
        university.setId(1);
        university.setName("Test University");
        university.setCreationDate(LocalDate.now());

        when(universityRepository.save(university)).thenReturn(university);

        ResponseEntity<University> response = universityService.createUniversity(university);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(university, response.getBody());
        verify(emailService).sendEmailToYourSelf(eq("createUniversity"), eq(university.toString()));
        verify(universityRepository).save(eq(university));
    }

    @Test
    void testGetAll() {
        University university = new University();
        university.setId(1);
        university.setName("Test University");
        university.setCreationDate(LocalDate.of(2021, 1, 1));
        when(universityRepository.findAll()).thenReturn(Collections.singletonList(university));
        List<University> universities = universityService.getAll();
        assertEquals(1, universities.size());
        assertEquals(university, universities.get(0));
    }

    @Test
    void testGetById() {
        University university = new University();
        university.setId(1);
        university.setName("Test University");
        university.setCreationDate(LocalDate.of(2021, 1, 1));
        when(universityRepository.findById(anyLong())).thenReturn(Optional.of(university));
        ResponseEntity<University> responseEntity = universityService.getById(1L);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(university, responseEntity.getBody());
    }

    @Test
    void testGetByIdNotFound() {
        when(universityRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<University> responseEntity = universityService.getById(1L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    @Test
    void testUpdateUniversity() {
        University university = new University();
        university.setId(1);
        university.setName("Test University");
        university.setCreationDate(LocalDate.of(2021, 1, 1));
        University updatedUniversity = new University();
        updatedUniversity.setId(1);
        updatedUniversity.setName("Updated University");
        updatedUniversity.setCreationDate(LocalDate.of(2021, 2, 1));
        when(universityRepository.findById(anyLong())).thenReturn(Optional.of(university));
        universityService.updateUniversity((long) updatedUniversity.getId(), updatedUniversity).getBody();
        University university1 = universityService.getById((long) university.getId()).getBody();
        assert university1 != null;
        assertEquals(updatedUniversity.getId(), university1.getId());
        assertEquals(updatedUniversity.getName(), university1.getName());
        assertEquals(updatedUniversity.getCreationDate(), university1.getCreationDate());
    }
}