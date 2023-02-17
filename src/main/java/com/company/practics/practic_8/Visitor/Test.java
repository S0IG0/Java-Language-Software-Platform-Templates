package com.company.practics.practic_8.Visitor;

public abstract class Test implements ProjectElement {
    public final Integer id;

    protected Test(Integer id) {
        this.id = id;
    }
}
