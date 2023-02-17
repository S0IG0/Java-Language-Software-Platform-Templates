package com.company.practics.practic_10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write bean name: ");
        String beanName = scanner.nextLine();

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext10.xml");
        Programmer programmer = (Programmer) context.getBean(beanName);
        programmer.doCoding();
    }
}
