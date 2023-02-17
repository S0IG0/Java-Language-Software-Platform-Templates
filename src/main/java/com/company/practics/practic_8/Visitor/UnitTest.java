package com.company.practics.practic_8.Visitor;

public class UnitTest extends Test{
    public final String name;

    public UnitTest(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public void beWritten(Developer developer) {
        developer.create(this);
    }

    @Override
    public String toString() {
        return "UnitTest{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
