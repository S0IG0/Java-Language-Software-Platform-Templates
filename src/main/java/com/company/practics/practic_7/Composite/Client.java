package com.company.practics.practic_7.Composite;

public record Client(Component component) {

    public void doSomething() {
        component.operation();
    }
}
