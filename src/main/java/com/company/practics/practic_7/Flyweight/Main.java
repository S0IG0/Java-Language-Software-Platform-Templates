package com.company.practics.practic_7.Flyweight;
// FlyweightFactory предоставляет доступ к легковесам.
// Flyweight - это абстрактный класс, который содержит метод operation(),
// который должен быть реализован конкретными легковесами.
// ConcreteFlyweight - это конкретный легковес, который хранит свою уникальную информацию.
// Client использует FlyweightFactory для получения легковесов и выполняет операции на них.
public class Main {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Client client1 = new Client(factory);
        Client client2 = new Client(factory);
        client1.doSomething("key1");
        client2.doSomething("key2");
    }
}

