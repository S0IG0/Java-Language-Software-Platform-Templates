package com.company.practics.practic_7.Composite;

public record Leaf(String name) implements Component {

    public void operation() {
        System.out.println("Leaf " + name + " operation.");
    }
}
