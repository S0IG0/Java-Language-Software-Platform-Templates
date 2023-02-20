package com.company.practics.practic_22.services.impl;

import com.company.practics.practic_22.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    @Override
    public void sendEmail(String emailTo, String subject, String massage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(massage);

        javaMailSender.send(mailMessage);
    }

    @Async
    @Override
    public void sendEmailToYourSelf(String subject, String massage) {
        sendEmail(username, subject, massage);
    }
}
