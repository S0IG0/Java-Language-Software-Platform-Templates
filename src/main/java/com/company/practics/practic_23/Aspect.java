package com.company.practics.practic_23;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Before("allServiceMethods()")
    public void logParameters(JoinPoint joinPoint) {
        log.info("Parameters: {}", joinPoint.getArgs());
    }
    @Pointcut("within(com.company.practics.practic_22.services.impl.*)")
    public void allServiceMethods() {}

}
