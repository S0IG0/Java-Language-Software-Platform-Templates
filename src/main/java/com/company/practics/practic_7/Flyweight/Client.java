package com.company.practics.practic_7.Flyweight;

public record Client(FlyweightFactory factory) {

    public void doSomething(String key) {
        Flyweight flyweight = factory.getFlyweight(key);
        flyweight.operation();
    }
}
