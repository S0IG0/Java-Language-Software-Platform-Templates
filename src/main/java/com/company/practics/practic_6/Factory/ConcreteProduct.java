package com.company.practics.practic_6.Factory;

class ConcreteProduct implements Product {
    @Override
    public void use() {
        System.out.println("Using concrete product");
    }
}