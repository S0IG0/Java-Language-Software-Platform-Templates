package com.company.practics.practic_6.Prototype;
// Shape - это абстрактный класс, определяющий метод clone() и метод draw(),
// который должен быть реализован в каждом конкретном классе.
// Rectangle и Circle - это конкретные классы, наследующиеся от Shape.
// ShapeCache - это класс, хранящий прототипы объектов и предоставля их. (фабрика)
public class Main {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());
        clonedShape1.draw();

        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        clonedShape2.draw();
    }
}
