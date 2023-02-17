package com.company.practics.practic_7.Flyweight;

public class ConcreteFlyweight extends Flyweight {
    private final String key;

    public ConcreteFlyweight(String key) {
        this.key = key;
    }

    public void operation() {
        System.out.println("ConcreteFlyweight with key: " + key);
    }
}
