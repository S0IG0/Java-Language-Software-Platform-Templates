package com.company.practics.practic_6.AbstractFactory;

public class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Painting Mac button");
    }
}