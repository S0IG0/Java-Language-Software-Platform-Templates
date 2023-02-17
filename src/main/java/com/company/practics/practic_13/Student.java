package com.company.practics.practic_13;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Student implements EnvironmentAware {
    private Environment environment;
    @Value("${program.student.name}")
    private String name;
    @Value("${program.student.last_name}")
    private String last_name;
    @Value("${program.student.group}")
    private String group;

    @PostConstruct
    public void init() {
        System.out.println(name);
        System.out.println(last_name);
        System.out.println(group);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
