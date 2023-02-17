package com.company.practics.practic_8.Visitor;

public abstract class Developer {
    public final String firstName;
    public final String secondName;

    public Developer(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public abstract void create(Class class_);
    public abstract void create(Database database);
    public abstract void create(Test test);
}
