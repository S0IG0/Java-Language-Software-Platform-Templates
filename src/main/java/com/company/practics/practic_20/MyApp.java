package com.company.practics.practic_20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableAspectJAutoProxy
public class MyApp {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(MyApp.class);
        app.run(args);
    }
}
