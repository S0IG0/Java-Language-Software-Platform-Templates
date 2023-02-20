package com.company.practics.practic_22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class MyApp {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(MyApp.class);
        app.run(args);
    }
}
