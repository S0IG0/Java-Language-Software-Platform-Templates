package com.company.practics.practic_6.AbstractFactory;

public class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Painting Win button");
    }
}