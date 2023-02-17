package com.company.practics.practic_8.Visitor;

public class JuniorDeveloper extends Developer{
    public final String email;

    public JuniorDeveloper(String firstName, String secondName, String email) {
        super(firstName, secondName);
        this.email = email;
    }

    @Override
    public void create(Class class_) {
        System.out.println("Write bad class... " + class_);
    }

    @Override
    public void create(Database database) {
        System.out.println("Crashed database... " + database);
    }

    @Override
    public void create(Test test) {
        System.out.println("Crashed more test and write bad tests... " + test);
    }
}
