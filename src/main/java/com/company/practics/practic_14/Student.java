package com.company.practics.practic_14;

public class Student {

    private static int count = 0;

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public Student(String firstName, String lastName, String middleName) {
        this.id = ++count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

}