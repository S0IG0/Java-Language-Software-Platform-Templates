package com.company.practics.practic_14;

import java.time.LocalDate;

public class University {
    private static int count = 0;

    private final int id;
    private final String name;
    private final LocalDate creationDate;

    public University(String name, LocalDate creationDate) {
        this.id = ++count;
        this.name = name;
        this.creationDate = creationDate;
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
