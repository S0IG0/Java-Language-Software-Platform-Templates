package com.company.practics.practic_22.services.impl;

import com.company.practics.practic_22.models.Student;
import com.company.practics.practic_22.models.University;
import com.company.practics.practic_22.repositories.StudentRepository;
import com.company.practics.practic_22.repositories.UniversityRepository;
import com.company.practics.practic_22.services.SchedulerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {
    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final String backupDirPath = "D:\\Projects\\Java\\TemplateJava\\backup\\";

    @Autowired
    public SchedulerServiceImpl(StudentRepository studentRepository, UniversityRepository universityRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
    }
    @Scheduled(fixedRate = 1800000) // каждые 30 минут
    public void cleanDirectoryAndBackupDatabase() throws IOException {
        log.info("cleanDirectoryAndBackupDatabase is started");
        // Очистка директории
        File backupDir = new File(backupDirPath);
        if (backupDir.exists()) {
            for (File file : Objects.requireNonNull(backupDir.listFiles())) {
                if (!file.delete()) {
                    log.info("is not deleted" + file);
                }
            }
        } else {
            if (!backupDir.mkdirs()) {
                log.info("mkdirs is not working");
            }
        }
        // Бэкап базы данных
        backupStudent();
        backupUniversity();
        log.info("cleanDirectoryAndBackupDatabase is finish");
    }

    private void backupStudent() throws IOException {
        log.info("backupStudent is started");
        List<Student> students = studentRepository.findAll();
        String filePath = backupDirPath +
                "/Student_backup_" +
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                        .replace("-", "_")
                        .replace(":", "_")
                        .replace(".", "_") +
                ".txt";
        FileWriter fileWriter = new FileWriter(filePath);
        ObjectMapper mapper = new ObjectMapper();
        for (Student student : students) {
            fileWriter.write(mapper.writeValueAsString(student) + "\n");
        }
        fileWriter.close();
        log.info("backupStudent is finish");
    }

    private void backupUniversity() throws IOException {
        log.info("backupUniversity is started");
        List<University> universities = universityRepository.findAll();
        String filePath = backupDirPath +
                "/University_backup_" +
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                        .replace("-", "_")
                        .replace(":", "_")
                        .replace(".", "_") +
                ".txt";
        FileWriter fileWriter = new FileWriter(filePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        for (University university : universities) {
            university.setStudents(new ArrayList<>());
            fileWriter.write(mapper.writeValueAsString(university) + "\n");
        }
        fileWriter.close();
        log.info("backupUniversity is finish");
    }
}
