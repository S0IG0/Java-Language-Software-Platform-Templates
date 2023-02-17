package com.company.practics.practic_8.Visitor;

public class Main {
    public static void main(String[] args) {
        Project project = new Project(new ProjectElement[]{
                new JavaClass("System.out.println(\"Hello world\");", "This code printing hello world"),
                new Postgresql("SQL", "Postgres", "localhost"),
                new UnitTest(19, "Unit test")
        });

        Developer junior = new JuniorDeveloper("Alex", "Brok", "brok@mail.com");
        Developer senior = new SeniorDeveloper("Alex", "Brok", "brok@mail.com", "+1453634563456", 2);

        project.beWritten(junior);
        project.beWritten(senior);

    }
}
