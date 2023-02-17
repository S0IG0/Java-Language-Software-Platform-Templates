package com.company.practics.practic_6.Factory;
// Product - это интерфейс для создания продукта.
// ConcreteProduct - это конкретная реализация Product.
// Creator - это абстрактный класс, определяющий factoryMethod, который возвращает Product.
// ConcreteCreator - это конкретная реализация Creator, которая создает ConcreteProduct.

public class Main {
    public static void main(String[] args) {
        ConcreteCreator concreteCreator = new ConcreteCreator();

        Product[] products = new Product[]{
                concreteCreator.factoryMethod(),
                concreteCreator.factoryMethod(),
                concreteCreator.factoryMethod(),
                concreteCreator.factoryMethod(),
        };

        for (Product product : products){
            product.use();
        }
    }
}
