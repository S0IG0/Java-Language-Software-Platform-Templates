package com.company.practics.practic_2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Human> humans = Arrays.asList(
                new Human(20, "John", "Doe", LocalDate.of(2001, 1, 1), 70),
                new Human(30, "Jane", "Doe", LocalDate.of(1991, 2, 2), 50),
                new Human(25, "Tom", "Smith", LocalDate.of(1996, 3, 3), 80),
                new Human(35, "Alice", "Brown", LocalDate.of(1986, 4, 4), 65)
        );

        long productOfAges = humans.stream()
                .sorted((h1, h2) -> h2.firstName().substring(1, 2).compareTo(h1.firstName().substring(1, 2)))
                .filter(h -> h.weight() > 60)
                .sorted((h1, h2) -> h1.age() - h2.age())
                .mapToLong(Human::age)
                .reduce(1, (a, b) -> a * b);

        System.out.println("Product of ages: " + productOfAges);
    }
}
