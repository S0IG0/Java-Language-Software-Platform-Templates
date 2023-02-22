package com.company.practics.practic_24;

import com.company.practics.practic_22.models.Student;
import com.company.practics.practic_22.repositories.StudentRepository;
import com.company.practics.practic_22.services.EmailService;
import com.company.practics.practic_22.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @InjectMocks
    StudentServiceImpl studentService;
    @Mock
    StudentRepository studentRepository;
    @Mock
    EmailService emailService;

    @Test
    void createStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setMiddleName("Smith");
        student.setId(1L);

        when(studentRepository.save(student)).thenReturn(student);

        ResponseEntity<Student> responseEntity = studentService.createStudent(student);

        verify(emailService).sendEmailToYourSelf(eq("createStudent"), anyString());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void getAll() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setMiddleName("Smith");
        student.setId(1L);

        List<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> retrievedStudents = studentService.getAll();

        verify(emailService).sendEmailToYourSelf(eq("get all students"), anyString());
        assertEquals(students, retrievedStudents);
    }

    @Test
    void getById() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setMiddleName("Smith");
        student.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        ResponseEntity<Student> responseEntity = studentService.getById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void updateStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setMiddleName("Smith");
        student.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student updatedStudent = new Student();
        updatedStudent.setFirstName("Jane");
        updatedStudent.setLastName("Doe");
        updatedStudent.setMiddleName("Smith");
        updatedStudent.setId(1L);

        when(studentRepository.save(student)).thenReturn(updatedStudent);

        ResponseEntity<Student> responseEntity = studentService.updateStudent(1L, updatedStudent);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedStudent, responseEntity.getBody());
    }


    @Test
    public void deleteStudent() {
        // Create a student to delete
        Student studentToDelete = new Student();
        studentToDelete.setFirstName("John");
        studentToDelete.setLastName("Doe");
        studentToDelete.setMiddleName("Smith");
        studentToDelete.setId(1L);
        studentService.createStudent(studentToDelete);
        Long createdStudentId = 1L;

        // Delete the student
        ResponseEntity<?> deleteResponse = studentService.deleteStudent(createdStudentId);
        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
    }
}
