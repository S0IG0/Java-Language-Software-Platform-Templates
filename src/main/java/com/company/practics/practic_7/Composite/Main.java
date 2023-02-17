package com.company.practics.practic_7.Composite;
// Component определяет общий метод operation(),
// который реализуют как листья (Leaf),
// так и композитные объекты (Composite).
// Leaf представляет отдельный объект, а Composite - группу объектов.
// Композитный объект содержит список Component, которые могут быть либо листьями, либо другими композитами.
// Клиент (Client) использует объекты типа Component и вызывает их метод operation().
public class Main {
    public static void main(String[] args) {
        Component component1 = new Leaf("Leaf 1");
        Component component2 = new Leaf("Leaf 2");
        Composite component3 = new Composite();
        component3.add(component1);
        component3.add(component2);
        Composite component4 = new Composite();
        component4.add(component3);
        Client client = new Client(component4);
        client.doSomething();
    }
}
