package com.company.practics.practic_21.services;

public interface EmailService {
    void sendEmail(
            String emailTo,
            String subject,
            String massage
    );

    void sendEmailToYourSelf(
            String subject,
            String massage
    );
}
