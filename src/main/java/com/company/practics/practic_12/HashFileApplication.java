package com.company.practics.practic_12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class  HashFileApplication {

    public String inputFileName = "src\\main\\java\\com\\company\\practics\\practic_12\\input.txt";
    public String outputFileName = "src\\main\\java\\com\\company\\practics\\practic_12\\output.txt";

    public static void main(String[] args) {
        SpringApplication.run(HashFileApplication.class, args);
    }

    @PostConstruct
    public void onStart() throws IOException, NoSuchAlgorithmException {
        if (inputFileName == null) {
            throw new IllegalArgumentException("Input file name is not provided");
        }

        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            createEmptyFile(outputFileName);
            return;
        }

        String hash = getFileHash(inputFile);
        writeHashToFile(hash, outputFileName);

        Files.delete(inputFile.toPath());
    }

    @PreDestroy
    public void onStop() throws IOException {
        File outputFile = new File(outputFileName);
        if (outputFile.exists()) {
            Files.delete(outputFile.toPath());
        }
    }

    private void createEmptyFile(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("null");
        }
    }

    private String getFileHash(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(Files.readAllBytes(file.toPath()));
        StringBuilder hashBuilder = new StringBuilder();
        for (byte hashByte : hashBytes) {
            hashBuilder.append(Integer.toHexString(0xff & hashByte));
        }
        return hashBuilder.toString();
    }

    private void writeHashToFile(String hash, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(hash);
        }
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
}
