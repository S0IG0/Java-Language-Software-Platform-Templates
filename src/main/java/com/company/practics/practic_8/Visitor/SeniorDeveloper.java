package com.company.practics.practic_8.Visitor;

public class SeniorDeveloper extends Developer{
    public final String email;
    public final String telephone;
    public final Integer coefficient;

    public SeniorDeveloper(String firstName, String secondName, String email, String telephone, Integer coefficient) {
        super(firstName, secondName);
        this.email = email;
        this.telephone = telephone;
        this.coefficient = coefficient;
    }

    @Override
    public void create(Class class_) {
        System.out.println("Write good code " + class_);
    }

    @Override
    public void create(Database database) {
        System.out.println("Create async access to database " + database);
    }

    @Override
    public void create(Test test) {
        System.out.println("Write good tests " + test);
    }
}
